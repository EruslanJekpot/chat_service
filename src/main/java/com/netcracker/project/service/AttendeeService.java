package com.netcracker.project.service;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public Attendee findByAttendeeId(UUID attendeeId){
        return attendeeRepository.findByAttendeeId(attendeeId);
    }

    public Attendee getAttendeeByUserId(String userId){
        return attendeeRepository.findAttendeeByUserId(userId);
    }

    public String getAttendeeSkills(UUID attendeeId){
        return attendeeRepository.findByAttendeeId(attendeeId).getSkills();
    }

    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    public byte[] extractBytes (String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
        return ( data.getData() );
    }
}