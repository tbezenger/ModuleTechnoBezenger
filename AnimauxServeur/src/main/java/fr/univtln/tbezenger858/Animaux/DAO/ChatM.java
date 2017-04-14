package fr.univtln.tbezenger858.Animaux.DAO;

import fr.univtln.tbezenger858.Animaux.Mammiferes.Chat;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chien;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Mammifere;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomy Bezenger on 28/02/2017.
 */
public class ChatM implements EntityManager<Chat>{
    Connection connection;

    public ChatM(Connection connection) {
        this.connection = connection;
    }

    ///////////////////////// a revoir si j'ai le temps ////////////////////////////
    static {
        try{
            Statement stmt = DriverManager.getConnection(Utils.url,Utils.user,Utils.password).createStatement();
            stmt.execute("CREATE TABLE MAMMIFERE (ID INT PRIMARY KEY, NBPATTES INT, NBMOUSTACHES INT);");
        } catch (Exception e) {
        }
    }
    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public Chat find(String id) throws Exception {
        Chat chat;
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM Mammifere WHERE id = "+id+";");
        ResultSet rs = stmt.getResultSet();
        rs.next();
        if (rs.getInt("nbMoustaches") != 0)
            chat = new Chat(rs.getInt("id"),rs.getInt("nbMoustaches"));
        else return null;
        return chat;
    }

    @Override
    public List<Chat> findall() throws Exception {
        List<Chat> chats = new ArrayList<Chat>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Mammifere");
        while (rs.next()){
            if (rs.getInt("nbMoustaches") != 0)
                chats.add(new Chat(rs.getInt("id"),rs.getInt("nbMoustaches")));
        }
        return chats;
    }

    @Override
    public boolean persist(Chat chat) throws Exception {
        Statement stmt = connection.createStatement();
        String query = "INSERT INTO MAMMIFERE VALUES ("+((Integer)chat.getId()).toString()+
                        ", 0, "+((Integer)(chat).getNbMoustaches()).toString() +");";
        return stmt.execute(query);
    }

    @Override
    public boolean remove(int id) throws Exception {
        Statement stmt = connection.createStatement();
        return stmt.execute("DELETE FROM MAMMIFERE WHERE ID = "+Integer.toString(id)+";");
    }

    @Override
    public boolean update(Chat chat) throws Exception {
        boolean b,c;
        connection.setAutoCommit(false);
        b=remove(chat.getId());
        c=persist(chat);
        connection.commit();
        connection.setAutoCommit(true);
        return (b==c && b);
    }
}
