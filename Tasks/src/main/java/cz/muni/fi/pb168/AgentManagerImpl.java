package cz.muni.fi.pb168;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by Vaclav on 8.3.14.
 */
public class AgentManagerImpl implements AgentManager
{
    private Connection connection;

    final static Logger log = LoggerFactory.getLogger(AgentManagerImpl.class);

    public AgentManagerImpl(Connection connection)
    {
        this.connection = connection;
        log.debug("AgentManager created");
    }

    @Override
    public Agent newAgent (String codename, Specialization spec) throws IllegalArgumentException
    {
        Agent createdAgent = null;

        if (codename == null)
        {
            codename = "";
        }

        try
        {
            PreparedStatement st =
                    connection.prepareStatement("INSERT INTO AGENTS (codename, specialization) VALUES (?,?)",
                            Statement.RETURN_GENERATED_KEYS);
            st.setString(1, codename);
            st.setString(2, spec.toString());
            st.execute();

            ResultSet keyRS = st.getGeneratedKeys();
            if(keyRS.next())
                createdAgent = new Agent(keyRS.getInt(1), codename, spec);
        }
        catch (SQLException e)
        {
            log.error("Error while creating new agent: " + e.getMessage());
            throw new ServiceFailureException("Error while creating new agent: " + e.getMessage());
        }

        return createdAgent;
    }
/*
    @Override
    public Agent findAgent(int agentID)
    {

    }

    @Override
    public Collection<Agent> listAgents()
    {

    }

    @Override
    public Collection<Agent> listAgents(Specialization spec)
    {

    }
    */
}
