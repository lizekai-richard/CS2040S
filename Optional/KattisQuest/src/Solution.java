import com.sun.source.tree.Tree;

import java.util.TreeSet;

class Quest implements Comparable<Quest> {
    public long energy;
    public long reward;

    public Quest(long energy, long reward) {
        this.energy = energy;
        this.reward = reward;
    }

    @Override
    public int compareTo(Quest q) {
        if (energy != q.energy) {
            return (int) (energy - q.energy);
        } else {
            return (int) (reward - q.reward);
        }
    }

}

public class Solution {
    // TODO: Include your data structures here
    TreeSet<Quest> q = new TreeSet<Quest>();
    private static long MAX_VALUE = Long.MAX_VALUE;

    public Solution() {
        // TODO: Construct/Initialise your data structures here
    }

    void add(long energy, long value) {
        // TODO: Implement your insertion operation here
        q.add(new Quest(energy, value));
    }

    long query(long remainingEnergy) {
        // TODO: Implement your query operation here
        long ans = 0;
        while (!q.isEmpty() && remainingEnergy > 0) {
            Quest t = new Quest(remainingEnergy, MAX_VALUE);
            Quest s = q.floor(t);
            if (s != null) {
                ans += s.reward;
                remainingEnergy -= s.energy;
                q.remove(s);
            } else {
                break;
            }
        }
        return ans;
    }

}
