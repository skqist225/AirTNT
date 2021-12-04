package com.airtnt.airtntapp.host;

import java.io.IOException;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.airtnt.airtntapp.FileUploadUtil;
import com.airtnt.airtntapp.room.RoomService;
import com.airtnt.common.entity.Image;
import com.airtnt.common.entity.Room;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/become-a-host/")
public class HostRestController {

    @Autowired
    private RoomService roomService;

    @PostMapping("upload-room-photos")
    public String uploadRoomPhotos(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute PhotoDTO payload)
            throws IOException, URISyntaxException {
        String uploadDir = "";

        if (payload.getFolderno() != null) {
            uploadDir = "../room_images/" + userDetails.getUsername() + "/" + payload.getFolderno();
            FileUploadUtil.cleanDir(uploadDir);
        } else
            uploadDir = "../room_images/" + userDetails.getUsername();

        for (MultipartFile multipartFile : payload.getPhotos()) {
            if (!multipartFile.isEmpty()) {
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }
        }

        JSONObject object = new JSONObject();
        object.put("status", "success");
        object.put("userName", userDetails.getUsername());

        return object.toString();
    }

    @PostMapping("update/upload-room-photos")
    public String updatedUploadRoomPhotos(@AuthenticationPrincipal UserDetails userDetails,
            @ModelAttribute PhotoDTO payload) throws IOException, URISyntaxException {
        String userName = userDetails.getUsername();
        String uploadDir = "../room_images/" + userName + "/" + payload.getFolderno();
        FileUploadUtil.cleanDir(uploadDir);

        Set<Image> newImages = new HashSet<>();

        for (MultipartFile multipartFile : payload.getPhotos()) {
            if (!multipartFile.isEmpty()) {

                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                System.out.println(fileName);
                newImages.add(new Image(fileName));
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            }
        }
        // do not assign new set // get new set and push it to old set
        Room room = roomService.getRoomById(Integer.parseInt(payload.getFolderno()));
        room.getImages().clear();
        for (Image i : newImages) {
            room.getImages().add(i);
        }

        try {
            roomService.save(room);
            return new JSONObject().put("status", "success").toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new JSONObject().put("status", "fail").toString();
    }

    @PostMapping("get-upload-photos")
    public String getUploadPhoto(@ModelAttribute GetPhoto payload) {
        String userName = payload.getUsername();
        String[] roomImages = payload.getRoomImages();

        String uploadDir = "";

        if (payload.getFolderno() != null) {
            uploadDir = "../room_images/" + userName + "/" + payload.getFolderno();
        } else
            uploadDir = "../room_images/" + userName;

        List<MultipartFile> multipartFiles = new ArrayList<>();

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
