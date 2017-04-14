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
public class ChienM implements EntityManager<Chien> {
    Connection connection;

    public ChienM(Connection connection) {
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
    public Chien find(String id) throws Exception {
        Chien chien;
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM Mammifere WHERE id = "+id+";");
        ResultSet rs = stmt.getResultSet();
        rs.next();
        if (rs.getInt("nbPattes") != 0)
            chien = new Chien(rs.getInt("id"),rs.getInt("nbPattes"));
        else
            return null;
        return chien;
    }

    @Override
    public List<Chien> findall() throws Exception {
        List<Chien> chiens = new ArrayList<Chien>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Mammifere");
        while (rs.next()){
            if (rs.getInt("nbPattes") != 0)
                chiens.add(new Chien(rs.getInt("id"),rs.getInt("nbPattes")));
        }
        return chiens;
    }

    @Override
    public boolean persist(Chien chien) throws Exception {
        Statement stmt = connection.createStatement();
        String query = "INSERT INTO MAMMIFERE VALUES ("+((Integer)chien.getId()).toString()+", "+
                ((Integer)chien.getNbPattes()).toString() +", 0); ";
        return stmt.execute(query);

    }

    @Override
    public boolean remove(int id) throws Exception {
        Statement stmt = connection.createStatement();
        String query = ("DELETE FROM MAMMIFERE WHERE ID = "+Integer.toString(id)+";");
        return stmt.execute(query);

    }

    @Override
    public boolean update(Chien chien) throws Exception {
        boolean b,c;
        connection.setAutoCommit(false);
        b=remove(chien.getId());
        c=persist(chien);
        connection.commit();
        connection.setAutoCommit(true);
        return (b==c && b);
    }
}
