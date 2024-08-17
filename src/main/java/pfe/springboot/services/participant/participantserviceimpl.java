package pfe.springboot.services.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.participant;
import pfe.springboot.repository.participantRepository;

@Service
public class participantserviceimpl implements participantserviceinter {
        @Autowired
    private participantRepository participantRepository;

       @Override
       public participant addparticipant(participant f) {
           BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           String encryptedPassword = passwordEncoder.encode(f.getPassword());
           f.setPassword(encryptedPassword);
           return participantRepository.save(f);
       }
       
    @Override
    public void savePhoto(Long id, MultipartFile file) throws IOException {
        try {
            participant participant = participantRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Participant not found"));
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            participant.setPhoto(file.getBytes());
            participantRepository.save(participant);
        } catch (IOException e) {
            // Handle the exception as needed
            throw new RuntimeException("Failed to save photo", e);
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public participant userid(Long id_participant) {
        return participantRepository.findById(id_participant).orElse(null);
    }

    @Override
    public participant updatePart(Long id_participant, participant participantmodifier) {
        // Check if the participant exists
        participant existingparticipant = participantRepository.findById(id_participant)
                .orElseThrow(() -> new RuntimeException("participant not found"));

        // Update the existing participant with the new values
        existingparticipant.setNom(participantmodifier.getNom());
        existingparticipant.setPrenom(participantmodifier.getPrenom());
        existingparticipant.setEmail(participantmodifier.getEmail());
        existingparticipant.setPassword(participantmodifier.getPassword());
        // Add any other fields that need to be updated

        // Save the updated participant
        return participantRepository.save(existingparticipant);
    }


    
    @Override
    public void deleteparticipant(Long id_participant) {
        participantRepository.deleteById(id_participant);

    }

    // @Override
    // public participant activateparticipant(Long id) {
    //     participant participant = participantRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("participant not found"));
    //     participant.setActive(true);
    //     return participantRepository.save(participant);
    // }

    // @Override
    // public participant deactivateparticipant(Long id) {
    //     participant participant = participantRepository.findById(id)
    //             .orElseThrow(() -> new RuntimeException("participant not found"));
    //     participant.setActive(false);
    //     return participantRepository.save(participant);
    // }

}
