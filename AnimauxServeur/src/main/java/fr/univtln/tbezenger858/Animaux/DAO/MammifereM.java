package fr.univtln.tbezenger858.Animaux.DAO;

import fr.univtln.tbezenger858.Animaux.Mammiferes.Chat;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Chien;
import fr.univtln.tbezenger858.Animaux.Mammiferes.Mammifere;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tomy Bezenger on 22/02/2017.
 */
public class MammifereM implements EntityManager<Mammifere> {
    // #JDBC
    Connection connection;

    ///////////////////////// a revoir si j'ai le temps ////////////////////////////
    static {
        try{
            Statement stmt = DriverManager.getConnection(Utils.url,Utils.user,Utils.password).createStatement();
            stmt.execute("CREATE TABLE MAMMIFERE (ID INT PRIMARY KEY, NBPATTES INT, NBMOUSTACHES INT);");
        } catch (Exception e) {
        }
    }
    /////////////////////////////////////////////////////////////////////////////////

    public MammifereM(Connection connection) throws SQLException {
        this.connection = connection;
    }

    @Override
    public Mammifere find(String id) throws Exception {
        Mammifere mammifere;
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM Mammifere WHERE id = "+id+";");
        ResultSet rs = stmt.getResultSet();
        rs.next();
        if (rs.getInt("nbPattes") != 0)
            mammifere = new Chien(rs.getInt("id"),rs.getInt("nbPattes"));
        else
            mammifere = new Chat(rs.getInt("id"),rs.getInt("nbMoustaches"));
        return mammifere;
    }

    @Override
    public List<Mammifere> findall() throws Exception {
        List<Mammifere> mammiferes = new ArrayList<Mammifere>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Mammifere");
        while (rs.next()){
            if (rs.getInt("nbPattes") != 0)
                mammiferes.add(new Chien(rs.getInt("id"),rs.getInt("nbPattes")));
            else
                mammiferes.add(new Chat(rs.getInt("id"),rs.getInt("nbMoustaches")));
        }
        return mammiferes;
    }

    @Override
    public boolean persist(Mammifere mammifere) throws Exception {
        Statement stmt = connection.createStatement();
        // enregistrer comme un chat si c'est un chat et comme un chien sinon
        String query = (mammifere.getClass() == Chat.class)?
                "INSERT INTO MAMMIFERE VALUES ("+((Integer)mammifere.getId()).toString()+
                        ", 0, "+((Integer)((Chat)mammifere).getNbMoustaches()).toString() +");"
                : "INSERT INTO MAMMIFERE VALUES ("+((Integer)mammifere.getId()).toString()+", "+
                ((Integer)((Chien)mammifere).getNbPattes()).toString() +", 0); ";
        return stmt.execute(query);
    }

    @Override
    public boolean remove(int id) throws Exception {
        Statement stmt = connection.createStatement();
        String query = "DELETE FROM MAMMIFERE WHERE ID = "+Integer.toString(id)+";";
        System.out.println(query);
        return stmt.execute(query);
    }

    @Override
    public boolean update(Mammifere mammifere) throws Exception {
        boolean b,c;
        connection.setAutoCommit(false);
        b=remove(mammifere.getId());
        c=persist(mammifere);
        connection.commit();
        connection.setAutoCommit(true);
        return (b==c && b);
    }
}
