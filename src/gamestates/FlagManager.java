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
      flags.put(flag, 0);
   }

   public boolean matchFlags(String[] yesFlags, String[] noFlags) {
      for (String flagName : yesFlags) {
         if (!this.flags.containsKey(flagName)) return false;
      }
      for (String flagName : noFlags) {
         if (this.flags.containsKey(flagName)) return false;
      }
      return true;
   }

   public void resetFlags() { flags.clear(); }

   public int getFlag(String flag) {
      return flags.getOrDefault(flag, 0);
   }

   public void setFlag(String flag, int value) {
      flags.put(flag, value);
   }
}
