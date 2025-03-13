import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An Election consists of the candidates running for office, the ballots that 
 * have been cast, and the total number of voters.  This class implements the 
 * ranked choice voting algorithm.
 * 
 * Ranked choice voting uses this process:
 * 
 * 1. Rather than vote for a single candidate, a voter ranks all the 
 * candidates.  For example, if 3 candidates are running on the ballot, a voter
 * identifies their first choice, second choice, and third choice.
 * 2. The first-choice votes are tallied.  If any candidate receives > 50% of 
 * the votes, that candidate wins.
 * 3. If no candidate wins &gt; 50% of the votes, the candidate(s) with the 
 * lowest number of votes is(are) eliminated.  For each ballot in which an
 * eliminated candidate is the first choice, the 2nd ranked candidate is now
 * the top choice for that ballot.
 * 4. Steps 2 & 3 are repeated until a candidate wins, or all remaining 
 * candidates have exactly the same number of votes.  In the case of a tie, 
 * there would be a separate election involving just the tied candidates.
 */
public class Election {
    // All candidates that were in the election initially.  If a candidate is 
    // eliminated, they will still stay in this array.
    private final Candidate[] candidates;
    
    // The next slot in the candidates array to fill.
    private int nextCandidate;
    
    /**
     * Create a new Election object.  Initially, there are no candidates or 
     * votes.
     * @param numCandidates the number of candidates in the election.
     */
    public Election (int numCandidates) {
        this.candidates = new Candidate[numCandidates];
    }
    
    /**
     * Adds a candidate to the election
     * @param name the candidate's name
     */
    public void addCandidate (String name) {
        candidates[nextCandidate] = new Candidate (name);
        nextCandidate++;
    }
    
    /**
     * Adds a completed ballot to the election.
     * @param ranks A correctly formulated ballot will have exactly 1 
     * entry with a rank of 1, exactly one entry with a rank of 2, etc.  If 
     * there are n candidates on the ballot, the values in the rank array 
     * passed to the constructor will be some permutation of the numbers 1 to 
     * n.
     * @throws IllegalArgumentException if the ballot is not valid.
     */
    public void addBallot (int[] ranks) {
        if (!isBallotValid(ranks)) {
            throw new IllegalArgumentException("Invalid ballot");
        }
        Ballot newBallot = new Ballot(ranks);
        assignBallotToCandidate(newBallot);
    }

    /**
     * Checks that the ballot is the right length and contains a permutation 
     * of the numbers 1 to n, where n is the number of candidates.
     * @param ranks the ballot to check
     * @return true if the ballot is valid.
     */
    private boolean isBallotValid(int[] ranks) {
        if (ranks.length != candidates.length) {
            return false;
        }
        int[] sortedRanks = Arrays.copyOf(ranks, ranks.length);
        Arrays.sort(sortedRanks);
        for (int i = 0; i < sortedRanks.length; i++) {
            if (sortedRanks[i] != i + 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines which candidate is the top choice on the ballot and gives the
     * ballot to that candidate.
     * @param newBallot a ballot that is not currently assigned to a candidate
     */
    private void assignBallotToCandidate(Ballot newBallot) {
        while (true) {
            int candidate = newBallot.getTopCandidate();
            if (candidates[candidate].isEliminated()) {
                newBallot.eliminateCandidate(candidate);
            }
            else {
                candidates[candidate].addBallot(newBallot);
                return;
            }
            
        }
    }
    
    /**
     * Apply the ranked choice voting algorithm to identify the winner.
     * @return If there is a winner, this method returns a list containing just
     * the winner's name is returned.  If there is a tie, this method returns a
     * list containing the names of the tied candidates.
     */
    public List<String> selectWinner () {
    	// String list that will be return when the winner is found
    	List<String> winnerList = new ArrayList <>();
    	
    	Candidate candidate;
    	String name;
    	
    	// Integer list with the number of votes for each candidate
    	List<Integer> voteNumList = voteCounts(candidates);
    	// candidate's index with the top votes
    	int topVoteId = topVoteCandidate(voteNumList);
    	
    	// get total number of votes in the election
    	int totalVoteNum = 0;
    	for (int i = 0; i < candidates.length; i++) {
    		totalVoteNum = totalVoteNum + candidates[i].getVotes();
    	}
    	
    	// if the top candidate has the number of votes above 50%
    	if (topVoteNum(voteNumList) > totalVoteNum/2) {
    		candidate = candidates[topVoteId];
    		name = candidate.getName();
    		winnerList.add(name);
    		return winnerList;
    	}
    	// if the top and bottom candidates have the same number of votes
    	else if (isTied(voteNumList) == true) {
    		for (int i = 0; i < voteNumList.size(); i++) {
    			if (voteNumList.get(i) == topVoteNum(voteNumList)) {
    				name = candidates[i].getName();
    				winnerList.add(name);
    			}
    		}
    		return winnerList;
    	}
    	// if both conditions are X
    	else {
    		// allocate the ballots that have the eliminated candidate
    		eliminateBallot(voteNumList);
    		// recursion
    		return selectWinner();
    	}
    }
    
    /**
     * get a list of number of first preference votes for each candidate
     * @param names array with names of all candidates
     * @return an arrayList with total votes for each candidate
     */
    private List<Integer> voteCounts (Candidate[] names){
    	List<Integer> voteNumList = new ArrayList<>();
    	for (Candidate candidate: names) {
    		voteNumList.add(candidate.getVotes());
    	}
    	return voteNumList;
    }
    
    /**
     * find the position of the candidate with the highest total votes
     * @param voteNumList an arrayList with total first preference votes for each candidate
     * @return an index of the candidate with the top votes
     */
    private int topVoteCandidate (List<Integer> voteNumList) {
    	int top = 0;
    	int topId = 0;
    	for (int i = 0; i < voteNumList.size(); i++) {
    		if (top < voteNumList.get(i)) {
    			top = voteNumList.get(i);
    			topId = i;
    		}
    	}
    	return topId;
    }
    
    /**
     * find the position of the candidate with the lowest total votes
     * @param voteNumList an arrayList with total first preference votes for each candidate
     * @return an index of the candidate with the least votes
     */
    private int eliminateCandidate (List<Integer> voteNumList) {
    	int eliminate = topVoteNum(voteNumList);
    	int eliminateId = topVoteCandidate(voteNumList);
    	for (int i = 0; i < voteNumList.size(); i++) {
    		if (eliminate > voteNumList.get(i)) {
    			eliminate = voteNumList.get(i);
    			eliminateId = i;
    		}
    	}
    	return eliminateId;
    }
    
    /**
     * find the highest vote count
     * @param voteNumList an arrayList with total first preference votes for each candidate
     * @return the highest vote count in the array
     */
    private int topVoteNum (List<Integer> voteNumList) {
    	int top = 0;
    	for (int i = 0; i < voteNumList.size(); i++) {
    		if (top < voteNumList.get(i)) {
    			top = voteNumList.get(i);
    		}
    	}
    	return top;
    }
    
    /**
     * check if there are tied candidates
     * @param voteNumList an arrayList with total first preference votes for each candidate
     * @return boolean; tied = true, not tied = false
     */
    private boolean isTied (List<Integer> voteNumList) {
    	boolean tie = false;
    	List<Integer> candidateNotTie = new ArrayList<>();
    	int topVoteNum = topVoteNum(voteNumList);
    	
    	// add votes that are not tied to the candidateNotTie array list
    	for (int vote : voteNumList) {
    		if (vote != topVoteNum) {
    			candidateNotTie.add(vote);
    		}
    	}
    	// check if the votes have been allocated
    	for (int num : candidateNotTie) {
    		if (num == 0) {
    			tie = true;
    		}
    	}
    	return tie;
    }
    
    /**
     * if there is no winner, eliminate the candidate with the lowest number of first preference votes
     * add a ballot to candidates that have second preference in the ballots an eliminated candidate is the first preference
     * @param voteNumList an arrayList with total first preference votes for each candidate
     */
    private void eliminateBallot(List<Integer> voteNumList ) {
    	// get index of candidate with least number of top choice vote
    	int eliminateRankId = eliminateCandidate(voteNumList);
    	Candidate name = candidates[eliminateRankId];
    	System.out.print("Eliminating " + name.getName());
    	System.out.println();
    	// eliminate the candidate
    	List<Ballot> toEliminate = name.eliminate();
    	// assign the ballot of the eliminated candidate to the second preference
    	for (int i = 0; i < toEliminate.size(); i++) {
    		assignBallotToCandidate(toEliminate.get(i));
    		
    	}
    }

}
    
