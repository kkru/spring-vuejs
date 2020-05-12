package app.messages.repository;

import java.util.List;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.messages.model.Message;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

@Component
public class MessageRepository {
    
    private final static Log log = LogFactory.getLog(MessageRepository.class);

    private SessionFactory sessionFactory;

    public MessageRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Message saveMessage(Message message){
        Session session = sessionFactory.getCurrentSession();
        session.save(message);
        return message;
    }

    public List<Message> getMessages() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Message";
        Query<Message> query = session.createQuery(hql, Message.class);
        return query.list();
    }

    //JDBC API
    /*
    public Message saveMessage(Message message) {
        Connection c = DataSourceUtils.getConnection(dataSource);
        try {
            String insertSql = "INSERT INTO messages (`id`, `text`, `created_date`) VALUE (null, ?, ?)";
            PreparedStatement ps = c.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getText());
            ps.setTimestamp(2, new Timestamp(message.getCreatedDate().getTime()));
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0) {
                ResultSet result = ps.getGeneratedKeys();
                if(result.next()) {
                    int id = result.getInt(1);
                    return new Message(id, message.getText(), message.getCreatedDate());
                } else {
                    log.error("Failed to retrieve id. NO row in result set.");
                    return null;
                }
            } else {
                //실패
                return null;
            }
        } catch (SQLException ex) {
            log.error("Failed to save message", ex);
            try {
                c.close();
            } catch (SQLException e) {
                log.error("Failed to close connection", e);
            }
        } finally {
            DataSourceUtils.releaseConnection(c, dataSource);
        }
        return null;
    }
    */

}