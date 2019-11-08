package com.netcracker.project.repository;

import com.netcracker.project.domain.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee,Long> {
    Attendee findByAttendeeId(Long id);
    @Modifying
    @Transactional
    @Query("update Attendee attendee set attendee.attendeeId = :attendee_id, attendee.email = :email, attendee.name = :name, " +
            "attendee.skills = :skills, attendee.surname = :surname where attendee.attendeeId = :attendee_id")
    void updateAttendee(@Param("attendee_id") Long id, @Param("email") String email,
                        @Param("name") String name, @Param("skills") String skills, @Param("surname") String surname);
}

