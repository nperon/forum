package fr.acceis.forum.hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.acceis.forum.beans.Post;
import fr.acceis.forum.beans.Topic;
import fr.acceis.forum.beans.Utilisateur;

public class CreateDatabase {
	public static void main(String[] args) {
		creerUtilisateurs();
	}

	private static void creerUtilisateurs() {
		SessionFactory sessionFactory = new Configuration().configure("/resources/hibernate-reset.cfg.xml").buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Date now = GregorianCalendar.getInstance().getTime();
		
		/* UTILISATEURS */
		Utilisateur admin = new Utilisateur();
		Utilisateur pierre =  new Utilisateur();
		Utilisateur paul =  new Utilisateur();
		Utilisateur jacques =  new Utilisateur();
		admin.setPosts(new ArrayList<Post>());
		admin.setPosts(new ArrayList<Post>());
		admin.setPosts(new ArrayList<Post>());
		admin.setPosts(new ArrayList<Post>());
		admin.setLogin("admin");
		pierre.setLogin("pierre");
		paul.setLogin("paul");
		jacques.setLogin("jacques");
		admin.setPassword("admin");
		pierre.setPassword("pierre");
		paul.setPassword("paul");
		jacques.setPassword("jacques");
		admin.setEmail("admin@acceis.fr");
		pierre.setEmail("pierre@acceis.fr");
		paul.setEmail("paul@acceis.fr");
		jacques.setEmail("jacques@acceis.fr");
		admin.setInscription(now);
		pierre.setInscription(now);
		paul.setInscription(now);
		jacques.setInscription(now);
		session.save( admin );
		session.save( pierre );
		session.save( paul );
		session.save( jacques );
		
		/* TOPICS */
		Topic lorem = new Topic();
		Topic curabitur = new Topic();
		lorem.setTitle("Lorem Ipsum");
		lorem.setPosts(new ArrayList<Post>());
		curabitur.setTitle("Curabitur");
		curabitur.setPosts(new ArrayList<Post>());
		session.save( curabitur );
		session.save( lorem );
		
		/* POSTS */
		String strA = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam diam tellus, "
				+ "sagittis ac nisl eu, dignissim rutrum purus. Aenean ultricies viverra venenatis. ";
		String strB = "Phasellus nunc tortor, consectetur vitae orci vel, porta pellentesque orci. "
				+ "Donec nec eros commodo, semper urna sit amet, gravida odio. Sed ac tellus sed "
				+ "sapien vulputate laoreet eu ut mi. ";
		String strC = "Aliquam sagittis lobortis laoreet. Cras nibh justo, porta eu sagittis quis, "
				+ "rhoncus ut neque.";
		String strD = "Quisque sagittis ut augue in aliquam. In accumsan in tortor commodo posuere. "
				+ "Vestibulum sit amet odio et nibh fermentum finibus sit amet suscipit magna. "
				+ "Maecenas luctus sapien id metus scelerisque, vel tristique massa tincidunt. ";
		String strE = "Curabitur vel augue nisi. Nulla facilisi. Sed viverra mi rutrum, tristique erat "
				+ "laoreet, convallis tellus. In venenatis dignissim urna, non suscipit massa luctus "
				+ "quis. Vestibulum pretium ex tortor, at consectetur ipsum condimentum non. ";
		String strF = "Sed tempus risus elementum, ultricies elit nec, porta mi. Proin et consequat odio. ";
		Post post1 = new Post();
		Post post2 = new Post();
		Post post3 = new Post();
		Post post4 = new Post();
		Post post5 = new Post();
		Post post6 = new Post();
		post1.setContent(strA);
		post2.setContent(strB);
		post3.setContent(strC);
		post4.setContent(strD);
		post5.setContent(strE);
		post6.setContent(strF);		
		post1.setDate(now);
		post2.setDate(now);
		post3.setDate(now);
		post4.setDate(now);
		post5.setDate(now);
		post6.setDate(now);
		post1.setAuthor(jacques);
		post2.setAuthor(pierre);
		post3.setAuthor(paul);
		post4.setAuthor(jacques);
		post5.setAuthor(pierre);
		post6.setAuthor(paul);
		post1.setTopic(lorem);
		post2.setTopic(lorem);
		post3.setTopic(lorem);
		post4.setTopic(lorem);
		post5.setTopic(curabitur);
		post6.setTopic(curabitur);
		lorem.getPosts().add(post1);
		lorem.getPosts().add(post2);
		lorem.getPosts().add(post3);
		lorem.getPosts().add(post4);
		curabitur.getPosts().add(post5);
		curabitur.getPosts().add(post6);				
		session.save( post1 );
		session.save( post2 );
		session.save( post3 );
		session.save( post4 );
		session.save( post5 );
		session.save( post6 );
		tx.commit();
		sessionFactory.close();
	}
}
