package py.una.sgf.controladores;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/multiple_upload")
	public String multiUpload() {

		return "multipleUpload";
	}

	@RequestMapping(value = "/abm/multiple_save", method = RequestMethod.POST)
	public @ResponseBody String multipleSave(
			@RequestParam("file") MultipartFile[] files,
			@RequestParam(required = true) String texto) {

		System.out
		.println("-------------------------------------------------------> "
				+ texto);
		String fileName = null;
		String msg = "";
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				try {
					fileName = files[i].getOriginalFilename();

					byte[] bytes = files[i].getBytes();

					BufferedOutputStream buffStream = new BufferedOutputStream(
							new FileOutputStream(new File(
									"/home/juan/Escritorio/" + fileName)));
					buffStream.write(bytes);
					buffStream.close();
					msg += "You have successfully uploaded " + fileName
							+ "<br/>";
				} catch (Exception e) {
					return "You failed to upload " + fileName + ": "
							+ e.getMessage() + "<br/>";
				}
			}
			return msg;
		} else {
			return "Unable to upload. File is empty.";
		}
	}
}
