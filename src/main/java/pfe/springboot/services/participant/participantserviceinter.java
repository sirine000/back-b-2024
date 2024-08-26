package pfe.springboot.services.participant;

import org.springframework.web.multipart.MultipartFile;

import pfe.springboot.entities.participant;

public interface participantserviceinter {
    participant addparticipant(participant f);

    participant updatePart(Long id_participant, participant updateProf);

    // participant userid(Long id_participant, participant prof);

    void deleteparticipant(Long id_participant);

    public participant userid(Long id_participant);

    public void savePhoto(Long id, MultipartFile file);
    // public participant activateparticipant(Long id);
    // public participant deactivateparticipant(Long id);

        public void sendPasswordResetToken(String email);
    
    public void resetPassword(String token, String newPassword);
}
