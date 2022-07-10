package jpaTest.main;

import jpaTest.domain.Game;
import jpaTest.domain.Steam;
import jpaTest.domain.SuperMapped.Users;
import jpaTest.domain.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class jpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
//            steamExample(em);
//            Orders orders = new Orders();
//            orders.addOrderItem(new OrderItem());

            itemSuperMovieTest(em);

//            Users user = new Users();
//            user.setName("USER!");
//            user.setCreateBy("park");
//            user.setCreateDate(LocalDateTime.now());

//            em.persist(user);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void itemSuperMovieTest(EntityManager em) {
        Movie movie = new Movie();
        movie.setName("movie");
        movie.setPrice(15000);
        movie.setActor("ACTOR A");
        movie.setDirector("DIRECTOR D");
        movie.setCreateBy("park");
        movie.setCreateDate(LocalDateTime.now());
        System.out.println("=======BEFORE PERSIST========");
        System.out.println("movie.getId() = " + movie.getId());
        System.out.println("============================");

        em.persist(movie);
        System.out.println("=======AFTER PERSIST========");
        System.out.println("movie.getId() = " + movie.getId());

        System.out.println("============================");

        em.flush();
        em.clear();

        Movie findMovie = em.find(Movie.class, movie.getId());
        System.out.println("findMovie = " + findMovie);
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
