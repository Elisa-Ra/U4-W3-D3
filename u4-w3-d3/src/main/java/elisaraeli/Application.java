package elisaraeli;

import elisaraeli.dao.EventiDAO;
import elisaraeli.entities.Evento;
import elisaraeli.entities.TipoEvento;
import elisaraeli.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("gestioneeventipu");

    public static void main(String[] args) {
        // Oggetto speciale che gestisce tutte le interazioni con il DB
        EntityManager em = entityManagerFactory.createEntityManager();
        EventiDAO eventd = new EventiDAO(em);

        Evento evento1 = new Evento("Evento 1",
                LocalDate.of(2026, 1, 20),
                "Descrizione dell'evento 1",
                TipoEvento.PUBBLICO,
                100
        );
        Evento evento2 = new Evento("Evento 2",
                LocalDate.of(2026, 2, 14),
                "Descrizione dell'evento 2",
                TipoEvento.PRIVATO,
                2
        );
        Evento evento3 = new Evento("Evento 3",
                LocalDate.of(2026, 12, 25),
                "Descrizione dell'evento 3",
                TipoEvento.PUBBLICO,
                500
        );

        // ************************************* SAVE *****************************

        eventd.save(evento1);
        eventd.save(evento2);
        eventd.save(evento3);

        // ************************************** FIND BY ID **********************
        try {
            Evento eventsFromDB = eventd.findById(1);
            System.out.println(eventsFromDB.getTitolo());

        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        // ************************************** FIND BY ID AND DELETE **********************
        try {
            eventd.findByIdAndDelete(2);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        // Best Practice. Quando finisco di utilizzare delle risorse come Scanner, EntityManager, EntityManagerFactory,
        // è sempre consigliato chiuderle

        em.close();
        entityManagerFactory.close();
    }
}