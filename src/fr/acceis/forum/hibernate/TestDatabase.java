package fr.acceis.forum.hibernate;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.acceis.forum.beans.Post;
import fr.acceis.forum.beans.Topic;
import fr.acceis.forum.beans.Utilisateur;


public class TestDatabase {
	private static SessionFactory sessionFactory = new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
	
	
	public static void main(String[] args) throws Exception {
		try{
			//listerUtilisateurs();
			//listerTopics();
			//listerPosts();
		} finally {
			sessionFactory.close();
		}
	}
	

	public static Utilisateur findUtilisateurById( long id ){
		SessionFactory sessionFactory = new Configuration().configure("/resources/hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession(); 
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Utilisateur> query = criteriaBuilder.createQuery(Utilisateur.class);		
		Utilisateur utilisateur = session.load(Utilisateur.class, id);
		return utilisateur;
	}
	
	public static Utilisateur findUtilisateurByLogin( String login ){
		Session session = sessionFactory.openSession(); 
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Utilisateur> query = criteriaBuilder.createQuery( Utilisateur.class );
		Root<Utilisateur> root = query.from( Utilisateur.class );
		query.select( root );
		query.where(criteriaBuilder.equal(root.get("login"), login));
		Utilisateur utilisateur = session.createQuery(query).uniqueResult();
		return utilisateur;
	}
	
	public static Topic findTopicById( long id ){
		Session session = sessionFactory.openSession();
		return session.load(Topic.class, id);
	};
	
	public static List<Topic> findAllTopics(){
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Topic> query = criteriaBuilder.createQuery(Topic.class);
		Root<Topic> root = query.from(Topic.class);
		query.select(root);
		List<Topic> result = session.createQuery(query).getResultList();
		return result;
	}
	
	public static Utilisateur createUtilisateur( Utilisateur utilisateur ){
		Utilisateur toSave = new Utilisateur();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		toSave.setLogin(utilisateur.getLogin());
		toSave.setPassword(utilisateur.getPassword());
		toSave.setEmail(utilisateur.getEmail());
		Date now = GregorianCalendar.getInstance().getTime();
		toSave.setInscription(now);
		session.save( toSave );
		tx.commit();
		Utilisateur saved = findUtilisateurByLogin( utilisateur.getLogin() );
		return saved;
	}

	/**
	 * 
	 * @param post
	 * @return
	 */
	public static Post createPost( Post post ){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		post.setIdPost((Long) session.save(post));
		tx.commit();
		return post;
	}

	/**
	 * 
	 * @param topic
	 * @return
	 */
	public static Topic createTopic( Topic topic ){
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		topic.setIdTopic((Long) session.save(topic));
		tx.commit();
		return topic;
	}

	
	
	private static Post findPostById(long id) {
		Session session = sessionFactory.openSession(); 
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Post> query = criteriaBuilder.createQuery( Post.class );
		Root<Post> root = query.from( Post.class );
		query.select( root );
		query.where(criteriaBuilder.equal(root.get("idPost"), id));
		Post post = session.createQuery(query).uniqueResult();
		return post;
	}


	public static void listerUtilisateurs(){
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Utilisateur> query = criteriaBuilder.createQuery(Utilisateur.class);
		Root<Utilisateur> root = query.from(Utilisateur.class);
		query.select(root);
		List<Utilisateur> utilisateurs = session.createQuery(query).getResultList();

		System.out.println("* Liste des utilisateurs :");
		for (Utilisateur u :utilisateurs) {
			System.out.println(u.getLogin()+" " +u.getEmail()+ " " + u.getId() + " " + u.getInscription()+ " " + u.getPassword());
		}
		System.out.println("* Fin de la liste des utilisateurs");
		System.out.println();
		
	}
	
	public static void listerTopics(){
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Topic> query = criteriaBuilder.createQuery(Topic.class);
		Root<Topic> root = query.from(Topic.class);
		query.select(root);
		List<Topic> topics = session.createQuery(query).getResultList();

		System.out.println("* Liste des topics :");
		for(Topic topic:topics){
			System.out.println(topic.getIdTopic()+". Titre:"+topic.getTitle());
			List<Post> posts = topic.getPosts();
			for(Post p: posts){
				Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateStr = formatter.format(p.getDate());
				System.out.print( "       par " + p.getAuthor().getLogin() + " date: " + dateStr );
				System.out.println( "       " + p.getContent());
			}
		}
		System.out.println("* Fin de la liste des topics");
		System.out.println();
	}

	
	public static void listerPosts(){
		Session session = sessionFactory.openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Post> query = criteriaBuilder.createQuery(Post.class);
		Root<Post> root = query.from(Post.class);
		query.select(root);
		List<Post> posts = session.createQuery(query).getResultList();
		System.out.println("* Liste des posts :");
		for(Post post:posts){
			System.out.print(post.getIdPost()+". Titre:"+post.getTopic().getTitle());
			System.out.print(" Auteur: "+post.getAuthor().getLogin());
			System.out.println(" Content: "+post.getContent());
			System.out.println();
		}
		System.out.println("* Fin de la liste des posts");
		System.out.println();
		
	}
}
