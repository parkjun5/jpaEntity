package jpaTest.main;

import jpaTest.domain.*;
import jpaTest.domain.SuperMapped.Users;
import jpaTest.domain.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

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

//            itemSuperMovieTest(em);

//            Users user = new Users();
//            user.setName("USER!");
//            user.setCreateBy("park");
//            user.setCreateDate(LocalDateTime.now());

//            em.persist(user);


            catAndHomeTEst(em);
//            lazyAndEagerDiff(em);
            Child child1 = new Child();
            Child child2 = new Child();
            Child child3 = new Child();

            Parent parent = new Parent();
            parent.setName("parent");

            parent.addChild(child1);
            parent.addChild(child2);
            parent.addChild(child3);

            em.persist(parent);
            em.flush();
            em.clear();
            Parent findParent = em.find(Parent.class, parent.getId());

            findParent.getChildList().remove(1);


            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void lazyAndEagerDiff(EntityManager em) {
        Home home = new Home();
        home.setName("wlq");
        em.persist(home);

        Home homeB = new Home();
        homeB.setName("집B");
        em.persist(homeB);

        Cat cat = new Cat();
        cat.setName("슬탕");
        cat.setHome(home);
        em.persist(cat);

        Cat cat2 = new Cat();
        cat2.setName("커피");
        cat2.setHome(homeB);
        em.persist(cat2);

        System.out.println("_++======================");
        List<Cat> cats = em.createQuery("select c from Cat c join fetch c.home", Cat.class)
                .getResultList();
        System.out.println("_++======================");

        for (Cat eac : cats) {
            System.out.println("cat.getHome().getName() = " + eac.getHome().getName());
        }
    }

    private static void catAndHomeTEst(EntityManager em) {
        Home home = new Home();
        home.setName("wlq");
        em.persist(home);

        Cat cat = new Cat();
        cat.setName("슬탕");
        cat.setHome(home);
        em.persist(cat);

        em.flush();
        em.clear();
        System.out.println("======================");

        Cat findCat = em.find(Cat.class, cat.getId());
        System.out.println("findCat.getName() = " + findCat.getClass());
        System.out.println("======================");

        Home catHome = findCat.getHome();
        System.out.println("catHome = " + catHome.getClass());
        System.out.println("======================");

        System.out.println("catHome = " + catHome.getName());

        System.out.println("======================");
        System.out.println("catHome = " + catHome.getClass());
//        List<Cat> cats = catHome.getCats();
//        for (Cat eachCat : cats) {
//            System.out.println("eachCat.getClass() = " + eachCat.getClass());
//            System.out.println("eachCat.getName() = " + eachCat.getName());
//        }
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
