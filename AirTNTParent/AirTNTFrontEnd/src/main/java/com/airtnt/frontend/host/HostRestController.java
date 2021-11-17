package com.airtnt.frontend.host;

import java.io.IOException;
import java.net.URISyntaxException;

import com.airtnt.frontend.FileUploadUtil;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

class Photos {
    private int currentFolderIndex;
    private MultipartFile[] photos;

    public int getCurrentFolderIndex() {
        return currentFolderIndex;
    }

    public void setCurrentFolderIndex(int currentFolderIndex) {
        this.currentFolderIndex = currentFolderIndex;
    }

    public MultipartFile[] getPhotos() {
        return photos;
    }

    public void setPhotos(MultipartFile[] photos) {
        this.photos = photos;
    }
}

@RestController
@RequestMapping("/become-a-host/")
public class HostRestController {
    private int count = 0;

    @PostMapping("upload-room-photos")
    public String uploadRoomPhotos(@ModelAttribute Photos payload) throws IOException, URISyntaxException {
        String uploadDir = "../room_images/" + "test/";
        if (payload.getCurrentFolderIndex() != 0)
            uploadDir += payload.getCurrentFolderIndex();
        else
            uploadDir += count;
        FileUploadUtil.cleanDir(uploadDir);
        for (MultipartFile multipartFile : payload.getPhotos()) {
            if (!multipartFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }
        }
        JSONObject object = new JSONObject();
        object.put("status", "successs");
        object.put("currentFolderIndex",
                payload.getCurrentFolderIndex() != 0 ? payload.getCurrentFolderIndex() : count);
        return object.toString();
    }
}
