package elisaraeli;

import elisaraeli.dao.EventiDAO;
import elisaraeli.dao.LocationDAO;
import elisaraeli.dao.PartecipazioneDAO;
import elisaraeli.dao.PersonaDAO;
import elisaraeli.entities.*;
import elisaraeli.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("gestioneeventipu");

    public static void main(String[] args) {

        EntityManager em = entityManagerFactory.createEntityManager();

        // DAO
        PersonaDAO personaDAO = new PersonaDAO(em);
        LocationDAO locationDAO = new LocationDAO(em);
        PartecipazioneDAO partecipazioneDAO = new PartecipazioneDAO(em);


        Persona persona = new Persona(
                "Elisa",
                "Raeli",
                "elisa@example.com",
                LocalDate.of(1997, 1, 1),
                "F"
        );
        personaDAO.save(persona);


        Location location = new Location(
                "Valle dei Templi",
                "Agrigento"
        );
        locationDAO.save(location);
        EventiDAO eventidao = getEventiDAO(em, location);

        // Prendo un evento salvato per creare la partecipazione
        Evento evento = eventidao.findById(1); // Evento 1

        Partecipazione partecipazione = new Partecipazione(
                persona,
                evento,
                StatoPartecipazione.CONFERMATA
        );
        partecipazioneDAO.save(partecipazione);

        // FIND BY ID
        try {
            Evento eventsFromDB = eventidao.findById(1);
            System.out.println(eventsFromDB.getTitolo());
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        //FIND BY ID AND DELETE
        try {
            eventidao.findByIdAndDelete(2);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        em.close();
        entityManagerFactory.close();
    }

    private static EventiDAO getEventiDAO(EntityManager em, Location location) {
        EventiDAO eventd = new EventiDAO(em);

        Evento evento1 = new Evento(
                "Evento 1",
                LocalDate.of(2026, 1, 20),
                "Descrizione dell'evento 1",
                TipoEvento.PUBBLICO,
                100,
                location
        );

        Evento evento2 = new Evento(
                "Evento 2",
                LocalDate.of(2026, 2, 14),
                "Descrizione dell'evento 2",
                TipoEvento.PRIVATO,
                2,
                location
        );

        Evento evento3 = new Evento(
                "Evento 3",
                LocalDate.of(2026, 12, 25),
                "Descrizione dell'evento 3",
                TipoEvento.PUBBLICO,
                500,
                location
        );

        eventd.save(evento1);
        eventd.save(evento2);
        eventd.save(evento3);

        return eventd;
    }
}


