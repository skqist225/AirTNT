package com.airtnt.frontend.host;

import java.io.IOException;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.airtnt.frontend.FileUploadUtil;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.mock.web.MockMultipartFile;

class GetPhoto {
    private String userName;
    private String[] roomImages;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String[] getRoomImages() {
        return roomImages;
    }

    public void setRoomImages(String[] roomImages) {
        this.roomImages = roomImages;
    }

}

@RestController
@RequestMapping("/become-a-host/")
public class HostRestController {

    @PostMapping("upload-room-photos")
    public String uploadRoomPhotos(@ModelAttribute PhotoDTO payload) throws IOException, URISyntaxException {
        String userName = payload.getUserName() != null ? payload.getUserName() : "no-name";

        String uploadDir = "../room_images/" + userName;

        // check here
        // FileUploadUtil.cleanDir(uploadDir);

        for (MultipartFile multipartFile : payload.getPhotos()) {
            if (!multipartFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }

        }
        JSONObject object = new JSONObject();
        object.put("status", "success");
        object.put("userName", userName);

        return object.toString();
    }

    @PostMapping("get-upload-photos")
    public String getUploadPhoto(@ModelAttribute GetPhoto payload) {
        System.out.println(payload);
        String userName = payload.getUserName();
        String[] roomImages = payload.getRoomImages();
        String uploadDir = "../room_images/" + userName;
        List<MultipartFile> multipartFiles = new ArrayList<>();

        System.out.println(userName);
        System.out.println(roomImages);

        String contentType = "text/plain";
        Path path = Paths.get(uploadDir);
        for (String name : roomImages) {

            Path fullPath = path.resolve(name);
            String originalFileName = name;
            String fileName = name;

            byte[] content = null;
            try {
                content = Files.readAllBytes(fullPath);
            } catch (final IOException e) {
            }
            MultipartFile result = new MockMultipartFile(fileName, originalFileName, contentType, content);
            multipartFiles.add(result);

        }
        JSONObject object = new JSONObject();
        object.put("status", "success");
        object.put("roomImages", multipartFiles);

        return object.toString();
    }
}
