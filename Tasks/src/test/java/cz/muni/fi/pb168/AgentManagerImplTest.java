package cz.muni.fi.pb168;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by Viktor on 8.3.14.
 */
public class AgentManagerImplTest
{
    private AgentManagerImpl manager;
    private Connection connection;

    @Before
    public void setUp() throws SQLException
    {
        String url="jdbc:derby://localhost:1527/mojeDB";
        connection = DriverManager.getConnection(url,"radek","heslo");

        //connection = DriverManager.getConnection("jdbc:derby:memory:MojeDB;create=true");
        connection.prepareStatement("CREATE TABLE AGENTS ("
                + "id bigint primary key generated always as identity,"
                + "codename VARCHAR(20),"
                + "specialization VARCHAR(15) CHECK(specialization IN('SILENT', 'BOMBER',"
                + "'SNIPER', 'HEAVYWEAPONS', 'MEDIC')))").execute();

        manager = new AgentManagerImpl(connection);
    }

    @After
    public void tearDown() throws SQLException {
        connection.prepareStatement("DROP TABLE RADEK.AGENTS").executeUpdate();
        connection.close();
    }

    @Test
    public void testAddAgent() throws Exception
    {
        Agent result = null;
        result = manager.newAgent("Libor S.", Specialization.SILENT);
        assertNotNull(result);
        assertEquals("Libor S.", result.getCodename());
        assertEquals(Specialization.SILENT, result.getSpecialization());
    }
/*
    @Test
    public void testFindAgent() throws Exception
    {
        // upravit, id resi db
        assertNull(manager.findAgent(0));
        assertNull(manager.findAgent(1));
        manager.addAgent(new Agent(0, Specialization.BOMBER));
        assertNotNull(manager.findAgent(0));
        assertNull(manager.findAgent(1));
    }

    @Test
    public void testListAgents() throws Exception
    {
        Agent a1 = new Agent(0, Specialization.BOMBER);
        Agent a2 = new Agent(2, Specialization.SNIPER);

        Set<Agent> expected = new HashSet<Agent>();
        expected.add(a1);
        expected.add(a2);

        assertTrue(manager.listAgents().isEmpty());
        manager.addAgent(a1);
        assertFalse(manager.listAgents().isEmpty());
        manager.addAgent(a2);
        assertEquals(expected, manager.listAgents());
    }

    @Test
    public void testListAgents1() throws Exception
    {
        Agent a1 = new Agent(0, Specialization.BOMBER);
        Agent a2 = new Agent(1, Specialization.BOMBER);
        Agent a3 = new Agent(2, Specialization.SNIPER);

        assertTrue(manager.listAgents().isEmpty());
        manager.addAgent(a1);
        assertTrue(manager.listAgents(Specialization.HEAVYWEAPONS).isEmpty());
        assertFalse(manager.listAgents(Specialization.BOMBER).isEmpty());
        manager.addAgent(a2);
        assertTrue(manager.listAgents(Specialization.BOMBER).size() == 2);
        manager.addAgent(a3);
        assertTrue(manager.listAgents(Specialization.BOMBER).size() == 2);

        Set<Agent> expected = new HashSet<Agent>();
        expected.add(a1);
        expected.add(a2);

        assertEquals(expected, manager.listAgents(Specialization.BOMBER));
    }
    */
}
