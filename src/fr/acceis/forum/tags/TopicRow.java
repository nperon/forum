package fr.acceis.forum.tags;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import fr.acceis.forum.beans.Topic;

public class TopicRow extends SimpleTagSupport {

	private Topic topic;

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	public void doTag() throws IOException {
		JspWriter out = getJspContext().getOut();
		if (topic != null) out.println(
			"								<tr>"+
			"									<td class=\"row1\"><a class=\"topictitle\" href=\"thread?id="+topic.getIdTopic()+"\" >"+topic.getTitle()+"</a></td>"+
			"									"+
			"									<td class=\"row2\" align=\"center\" width=\"130\">"+
			"										<p class=\"topicauthor\">"+
			"											<a class=\"username-coloured\" href=\"#\">"+topic.getPosts().get(0).getAuthor().getLogin()+"</a>"+
			"										</p>"+
			"									</td>"+
			"									<td class=\"row1\" align=\"center\" width=\"50\"><p class=\"topicdetails\">"+topic.getPosts().size()+"</p></td>"+
			"									<td class=\"row2\" align=\"center\" width=\"50\"><p class=\"topicdetails\">1234</p></td>"+
			"								</tr>"
		);
	}
}