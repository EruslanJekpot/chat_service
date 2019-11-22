package com.netcracker.project.repository;

import com.netcracker.project.domain.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.nio.file.Path;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, UUID> {
    Attendee findByAttendeeId(UUID id);
}

