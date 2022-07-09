package jpaTest.main;

import jpaTest.domain.Game;
import jpaTest.domain.OrderItem;
import jpaTest.domain.Orders;
import jpaTest.domain.Steam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//            steamExample(em);
            Orders orders = new Orders();
            orders.addOrderItem(new OrderItem());


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void steamExample(EntityManager em) {
        Steam steam = new Steam();
        steam.setName("keariPlz");
        em.persist(steam);

        Game game = new Game();
        game.setPrice(1000);
        game.setTitle("monsterHunter");
        game.setSteam(steam);
        steam.getGames().add(game);
        em.persist(game);

        Game game2 = new Game();
        game2.setPrice(69000);
        game2.setTitle("pokemon");
        game2.setSteam(steam);
        steam.getGames().add(game2);
        em.persist(game2);

        Game game3 = new Game();
        game3.setPrice(55000);
        game3.setTitle("bloodBorn");
        game3.setSteam(steam);
        steam.getGames().add(game2);
        em.persist(game3);

//            em.flush();
//            em.clear();

        Steam steam1 = em.find(Steam.class, 1L); //1 차캐시
        for (Game steam1Game : steam1.getGames()) {
            System.out.println(steam1Game.getSteam().getName());
            steam1Game.setPrice(20000);
        }
    }
}
