package com.davidmiguel.relayrace.agents;

import com.davidmiguel.relayrace.behaviours.ExperimentBehaviour;

import jade.core.Agent;
import jade.util.Logger;

/**
 * JudgeAgent guides the execution of the experiment, gives the start signal,
 * time the execution time and saves the results.
 * 
 * > Parameters:
 * 1. numAttempts: total number of attempts to run.
 * 2. initLaps: number of laps in first attemp.
 * 3. step: number of laps to increase in each attempt.
 */
public class JudgeAgent extends Agent {

	private final Logger logger = Logger.getMyLogger(getClass().getName());
	private static final long serialVersionUID = 934303410329286008L;

	private int numAttempts;
	private int initLaps;
	private int step;

	@Override
	protected void setup() {
		// Get arguments (nAttempts, initLaps, step)
		Object[] args = getArguments();
		if (args != null && args.length == 3) {
			numAttempts = Integer.parseInt((String) args[0]);
			initLaps = Integer.parseInt((String) args[1]);
			step = Integer.parseInt((String) args[2]);
			logger.info("Init experiment (a:" + numAttempts + ", i:" + initLaps + ", s:" + step);
		} else {
			logger.log(Logger.SEVERE, "Agent " + getLocalName() + " - Incorrect number of arguments");
			doDelete();
		}
		// Init experiment
		addBehaviour(new ExperimentBehaviour(numAttempts, initLaps, step));
	}
}