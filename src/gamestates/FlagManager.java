package gamestates;

import java.util.HashMap;
import java.util.Map;

public class FlagManager {
   private final Map<String, Integer> flags;

   public FlagManager() {
         this.flags = new HashMap<>();
   }

   public void addFlag(String flag) {
      flags.put(flag, 1);
   }

   public void removeFlag(String flag) {
      flags.remove(flag);
   }

   public boolean matchFlags(String[] yesFlags, String[] noFlags) {
      for (String flagName : yesFlags) {
         if (!hasFlag(flagName)) return false;
      }
      for (String flagName : noFlags) {
         if (hasFlag(flagName)) return false;
      }
      return true;
   }

   public boolean hasFlag(String flag) {
      return flags.containsKey(flag);
   }

   public void resetFlags() { flags.clear(); }

   public int getFlag(String flag) {
      return flags.getOrDefault(flag, 0);
   }
   
   public Map<String, Integer> getFlags() {
       return flags;
   }

   public void setFlag(String flag, int value) {
      flags.put(flag, value);
   }

   public void printFlags() {
      for (Map.Entry<String, Integer> entry : flags.entrySet()) {
         System.out.println(entry.getKey() + ": " + entry.getValue());
      }
   }
}
