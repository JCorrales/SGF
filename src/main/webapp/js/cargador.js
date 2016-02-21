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

	var windowHalfX = (window.innerWidth / 2) -280;
	var windowHalfY = (window.innerHeight / 2) -50;
	var clock = new THREE.Clock();

	function init(data) {
		container = $('#pantalla');

		var info = document.createElement( 'div' );
		info.style.position = 'absolute';
		info.style.top = '10px';
		info.style.width = '100%';
		info.style.textAlign = 'center';
		info.innerHTML = 'Drag to spin the cube';
		$('#pantalla').append( info );

		camera = new THREE.OrthographicCamera( window.innerWidth / - 2, window.innerWidth / 2, 
			window.innerHeight / 2, window.innerHeight / - 2, -10000, 10000 );//-10000 10000 frustum view
		//camera.position.y = 150;
		//camera.position.x = 80;
		camera.position.z = 1000;//10 metros
		//camera.rotation.y = 0.5;

		scene = new THREE.Scene();
		var datos = data.valueOf();
		//contenedor
	
		var geometry = new THREE.BoxGeometry( datos[0].largo, datos[0].alto, datos[0].ancho);
		var material = new THREE.MeshBasicMaterial( { color:datos[0].color, opacity: 0.1, wireframe:true});
		var cube = new THREE.Mesh( geometry, material );

		cube.position.x = datos[0].x;
		cube.position.y = datos[0].y;
		cube.position.z = datos[0].z;
		scene.add( cube );

		// paquetes
		
		for (i = 1; i < datos.length; i++) {
			var ancho = datos[i].ancho;
			var alto = datos[i].alto;
			var largo = datos[i].largo;
			var x = datos[i].x;
			var y = datos[i].y;
			var z = datos[i].z;
			var color = datos[i].color;
		
			var geometry = new THREE.BoxGeometry( largo, alto, ancho );
			var material = new THREE.MeshBasicMaterial( { color:color, opacity: 1});
			var cube = new THREE.Mesh( geometry, material );

			cube.position.x = x;
			cube.position.y = y;
			cube.position.z = z;
			scene.add( cube );
			//break;
		}

		renderer = new THREE.CanvasRenderer();
		renderer.setClearColor( 0xf0f0f0 );
		renderer.setPixelRatio( window.devicePixelRatio );
		renderer.setSize( window.innerWidth, window.innerHeight);
		
		controls = new THREE.OrbitControls( camera, renderer.domElement );
		//controls.addEventListener( 'change', render ); // add this only if there is no animation loop (requestAnimationFrame)
		controls.enableDamping = true;
		controls.dampingFactor = 0.25;
		controls.enableZoom = true;

		stats = new Stats();
		stats.domElement.style.position = 'absolute';
		stats.domElement.style.top = '0px';
		$('#pantalla').append( stats.domElement );
		$('#pantalla').append( renderer.domElement );
		}

		function animate() {

			requestAnimationFrame( animate );

			render();
			stats.update();

		}

		function render() {
			var delta = clock.getDelta();
			controls.update( delta );
			renderer.render( scene, camera );

		}





//nada que ver
function serializer(obj) {

	var t = typeof (obj);
	if (t != "object" || obj === null) {

		// simple data type
		if (t == "string") obj = '"'+obj+'"';
		return String(obj);

	}
	else {

		// recurse array or object
		var n, v, json = [], arr = (obj && obj.constructor == Array);

		for (n in obj) {
			v = obj[n]; t = typeof(v);

			if (t == "string") v = '"'+v+'"';
			else if (t == "object" && v !== null) v = JSON.stringify(v);

			json.push((arr ? "" : '"' + n + '":') + String(v));
		}

		return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
	}
};
