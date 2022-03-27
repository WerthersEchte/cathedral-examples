package de.fhkiel.ki.examples.gui.withAi;


import de.fhkiel.ki.ui.CathedralGUI;

public class RunGuiWithAi {
  public static void main(String[] args) {
    CathedralGUI.launchGUI(new CoolAI());
  }
}
