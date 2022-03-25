package traininglog;

import java.util.Comparator;

public class WorkoutComparator implements Comparator<Workout>{

    private boolean dateSort;

    public WorkoutComparator(boolean dateSort) {
        this.dateSort = dateSort;
    }

    private int getYear(Workout w) {
        return Integer.parseInt(w.getDate().substring(6));
    }
    private int getMonth(Workout w) {
        return Integer.parseInt(w.getDate().substring(3,5));
    }
    private int getDay(Workout w) {
        return Integer.parseInt(w.getDate().substring(0,2));
    }
    

    @Override
    public int compare(Workout o1, Workout o2) {
        if (this.dateSort == false) {
            if (getYear(o1) != getYear(o2)){
                return getYear(o1) - getYear(o2);
            }
            else if (getMonth(o1) != getMonth(o2)){
                return getMonth(o1) - getMonth(o2);
            }
            else {
                return getDay(o1) - getDay(o2);
            }
        }
        else {
            return (int) o1.getTime() - o2.getTime();
        }
    }
    
}
