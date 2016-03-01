/*
<script src="../build/three.min.js"></script>
<script src="js/renderers/Projector.js"></script>
<script src="js/renderers/CanvasRenderer.js"></script>

<script src="js/libs/stats.min.js"></script>

<script src="js/controls/FlyControls.js"></script>
<script src="js/controls/OrbitControls.js"></script>

<script src="js/Detector.js"></script>

*/

/*****************************************************************/
	var container, stats;

	var camera, scene, renderer;

	var cube, plane;

	var clock = new THREE.Clock();

	function init(data) {
		container = $('#pantalla');
		var width = container.css('width').replace('px', '');
		var height = container.css('height').replace('px', '');

		camera = new THREE.OrthographicCamera( width / - 2, width / 2, 
			height / 2, height / - 2, -10000, 10000 );//-10000 10000 frustum view
		camera.position.y = 500;
		camera.position.x = 500;
		camera.position.z = 1000;//10 metros
		//camera.rotation.y = 0.5;

		scene = new THREE.Scene();
		var datos = data.valueOf();
		
		//contenedor
    	addContenedor(datos, scene);

		// paquetes
		addPaquetes(datos, scene);

		//renderer = new THREE.CanvasRenderer();
		if ( webglAvailable() ) {
			renderer = new THREE.WebGLRenderer();
		} else {
			renderer = new THREE.CanvasRenderer();
		}
		renderer.setClearColor( 0xf0f0f0 );
		renderer.setPixelRatio( window.devicePixelRatio );
		renderer.setSize( width, height);
		
		//controls
		var target = new THREE.Vector3(datos[0].x, datos[0].y, datos[0].z);//no funciona si uso la variable contenedor
		controls = new THREE.OrbitControls( camera, renderer.domElement, target );
		//controls.addEventListener( 'change', render ); // add this only if there is no animation loop (requestAnimationFrame)
		controls.enableDamping = true;
		controls.dampingFactor = 0.25;
		controls.enableZoom = true;

		$('#pantalla').append( renderer.domElement );
	}

	function animate() {

		requestAnimationFrame( animate );
		render();
	}

	function render() {

		var delta = clock.getDelta();
		controls.update( delta );
		renderer.render( scene, camera );
	}

	function webglAvailable() {

		try {
			var canvas = document.createElement( 'canvas' );
			return !!( window.WebGLRenderingContext && (
				canvas.getContext( 'webgl' ) ||
				canvas.getContext( 'experimental-webgl' ) )
			);
		} catch ( e ) {
			return false;
		}
	}

	function addContenedor(datos, scene){
		var geometry = new THREE.BoxGeometry( datos[0].largo, datos[0].alto, datos[0].ancho);
		var material = new THREE.MeshBasicMaterial( { color:datos[0].color, opacity: 0.3, wireframe: true, transparent: true});
		var contenedor = new THREE.Mesh( geometry, material );

		contenedor.position.x = datos[0].x;
		contenedor.position.y = datos[0].y;
		contenedor.position.z = datos[0].z;

		var contenedorHelp = new THREE.BoxHelper( contenedor );
    	contenedorHelp.material.color.set( 0x000000 );
    	contenedorHelp.material.linewidth = 1;
    	scene.add( contenedorHelp );
    	//scene.add( contenedor ); solo agregamos el helper
	}

	function addPaquetes(datos, scene){
		for (i = 1; i < datos.length; i++) {
			var ancho = datos[i].ancho;
			var alto = datos[i].alto;
			var largo = datos[i].largo;
			var x = datos[i].x;
			var y = datos[i].y;
			var z = datos[i].z;
			var color = datos[i].color;
		
			var geometry = new THREE.BoxGeometry( largo, alto, ancho );
			var material = new THREE.MeshBasicMaterial( { color:color, transparent: true, opacity: 0.7});
			var cube = new THREE.Mesh( geometry, material );

			cube.position.x = x;
			cube.position.y = y;
			cube.position.z = z;

			//para los bordes de los cubos
			var helper = new THREE.BoxHelper( cube );
    		helper.material.color.set( 0xffffff );
    		helper.material.linewidth = 1;
    		scene.add( helper );
			scene.add( cube );
		}
	}