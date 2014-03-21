package cz.muni.fi.pb168;

/**
 * Created by Karel on 8.3.14.
 */
public interface AgentManager
{
    public Agent newAgent (String codename, Specialization spec);
/*
    Agent findAgent(int agentID);

    Iterable<Agent> listAgents();

    Iterable<Agent> listAgents(Specialization spec);
*/
}
