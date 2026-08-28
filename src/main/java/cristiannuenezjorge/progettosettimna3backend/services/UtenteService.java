package cristiannuenezjorge.progettosettimna3backend.services;

import cristiannuenezjorge.progettosettimna3backend.entities.Utente;
import cristiannuenezjorge.progettosettimna3backend.exceptions.NotFoundException;
import cristiannuenezjorge.progettosettimna3backend.payloads.AggiornaRuoloDTO;
import cristiannuenezjorge.progettosettimna3backend.payloads.UtenteRespDTO;
import cristiannuenezjorge.progettosettimna3backend.repositories.UtenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Transactional(readOnly = true)
    public List<UtenteRespDTO> trovaTutti() {
        return utenteRepository.findAll().stream().map(UtenteRespDTO::da).toList();
    }

    @Transactional(readOnly = true)
    public UtenteRespDTO trovaPerId(UUID id) {
        return UtenteRespDTO.da(trovaEntitaPerId(id));
    }

    @Transactional
    public UtenteRespDTO cambiaRuolo(UUID id, AggiornaRuoloDTO body) {
        Utente utente = trovaEntitaPerId(id);
        utente.setRuolo(body.ruolo());
        return UtenteRespDTO.da(utenteRepository.save(utente));
    }

    public Utente trovaEntitaPerId(UUID id) {
        return utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nessun utente trovato con id " + id));
    }
}