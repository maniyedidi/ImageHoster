package ImageHoster.repository;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;

import java.util.List;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the Comment object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public void uploadComment(Comment comment) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch all the comments from the database by given imgae ID
    //Returns the list of all the comments fetched from the database
    public List<Comment> getAllComments(Integer imageId) {

        List<Comment> resultList=null;
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c WHERE c.image.id = :id", Comment.class).setParameter("id", imageId);
            resultList = query.getResultList();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultList;
    }

    public void deleteComments(Integer commentId){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Comment comment = em.find(Comment.class, commentId);
            em.remove(comment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }

    }

}