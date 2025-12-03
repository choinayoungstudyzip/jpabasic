package jpabook.jpashop;

import jpabook.jpashop.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpashop");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // setOrder하지 않고 addOrderItem 메소드 사용할수도 있음
            // order.addOrderItem(new OrderItem());
            // 연관관계 주인은 정해져있지만 값 세팅은 어차피 양쪽에 들어가게 할 거니까 내가 원하는 곳에 편의메소드 만들어서 사용하면 됨.
            // 메소드는 둘 중에 한 쪽에서만 해야됨 ** 무한루프 걸릴 수도 있음.

            // 영속성 컨텍스트 때문에 flush 해주면 DB에서 값 가져온거
            // 이 부분을 지우면 1차캐시에서 조회해오는거라 join해서 안해주면 비어있음.
            // em.flush();
            // em.clear();

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            em.persist(book);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
