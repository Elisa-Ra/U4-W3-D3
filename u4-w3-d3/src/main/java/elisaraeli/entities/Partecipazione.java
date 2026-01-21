package elisaraeli.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "partecipazione")

public class Partecipazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "persona", nullable = false)
    private long persona;

    @Column(name = "evento", nullable = false)
    private long evento;

    @Column(name = "stato", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatoPartecipazione statoPartecipazione;

    public Partecipazione() {
    }

    public Partecipazione(long persona, long evento, StatoPartecipazione statoPartecipazione) {
        this.persona = persona;
        this.evento = evento;
        this.statoPartecipazione = statoPartecipazione;
    }

    // GETTER E SETTER
    
    public long getId() {
        return id;
    }

    public long getPersona() {
        return persona;
    }

    public void setPersona(long persona) {
        this.persona = persona;
    }

    public long getEvento() {
        return evento;
    }

    public void setEvento(long evento) {
        this.evento = evento;
    }

    public StatoPartecipazione getStatoPartecipazione() {
        return statoPartecipazione;
    }

    public void setStatoPartecipazione(StatoPartecipazione statoPartecipazione) {
        this.statoPartecipazione = statoPartecipazione;
    }
}
