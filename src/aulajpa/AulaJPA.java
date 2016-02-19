package aulajpa;

import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AulaJPA {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("AulaJPAPU");

        Automovel auto = new Automovel();
        auto.setPlaca("gwt-1010");
        auto.setDataAtualizacao(Calendar.getInstance().getTime());
        auto.setAnoFabricacao(2010);
        auto.setMarca("VW");
        auto.setModelo("Gol");
        auto.setObservacoes("Nunca foi batido");
        Acessorio a = new Acessorio();
        a.setNome("Ar condicionado");
        auto.getAcessorios().add(a);
a = new Acessorio();
        a.setNome("Direção hidráulica");
        auto.getAcessorios().add(a);
        persistir(emf, auto);

        auto = new Automovel();
        auto.setPlaca("gwt-1011");
        auto.setDataAtualizacao(Calendar.getInstance().getTime());
        auto.setAnoFabricacao(2006);
        auto.setMarca("Fiat");
        auto.setModelo("UNO");
        auto.setObservacoes("Pneus novos");
        
        Dono dono = new Dono();
        dono.setNome("Paulo");
        auto.setDono(dono);

        persistir(emf, auto);

        listarTodos(emf);

        System.out.println("*** Listando por marcas ***");

        listarTodosPorMarca(emf, "VW");
        
        System.out.println("*** Buscar pela chave com find ***");

        buscarPelaChaveComFind(emf, new Long(3));
        
        System.out.println("*** Buscar pela chave com getReference ***");

        buscarPelaChaveComGetReference(emf, new Long(3));
        
        emf.close();

    }
    
    public static void buscarPelaChaveComFind(EntityManagerFactory emf, Long ch) {
        EntityManager em = emf.createEntityManager();
        Automovel auto = em.find(Automovel.class, ch);
        System.out.println(auto);
        em.close();
    }
    
    public static void buscarPelaChaveComGetReference(EntityManagerFactory emf, Long ch) {
        EntityManager em = emf.createEntityManager();
        Automovel auto = em.getReference(Automovel.class, ch);
        System.out.println(auto);
        em.close();
    }

    public static void listarTodos(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Automovel> tq = em.createQuery("select a from Automovel a", Automovel.class);
        List<Automovel> autos = tq.getResultList();

        for (Automovel a : autos) {
            System.out.println(a);
            if (a.getDono() != null) {
                System.out.println(a.getDono().getNome());
            }
            if (!a.getAcessorios().isEmpty()) {
                for(Acessorio ac : a.getAcessorios()) {
                    System.out.println(ac.getNome());
                }
            }
        }
        em.close();
    }

    public static void listarTodosComNamedQuery(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Automovel> tq = em.createNamedQuery(Automovel.LISTAR_TODOS, Automovel.class);
        List<Automovel> autos = tq.getResultList();

        for (Automovel a : autos) {
            System.out.println(a);
        }
        em.close();
    }

    public static void listarTodosPorMarca(EntityManagerFactory emf, String marca) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Automovel> tq = em.createQuery("select a from Automovel a where a.marca = :marca", Automovel.class);
        tq.setParameter("marca", marca);
        List<Automovel> autos = tq.getResultList();

        for (Automovel a : autos) {
            System.out.println(a);
        }
        em.close();
    }
    
    

    public static void persistir(EntityManagerFactory emf, Automovel auto) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(auto);
        em.getTransaction().commit();

        em.close();
    }

}
