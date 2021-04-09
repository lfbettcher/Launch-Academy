public class PC extends Computer {

  public PC(int availableMemoryMb, int availableDiskSpaceMb) {
    super(availableMemoryMb, availableDiskSpaceMb);
  }

  @Override
  public String toString() {
    return super.toString() + " Operating System: PC";
  }

  public void upgradeMemory(int memoryToAddMb) {
    this.setAvailableMemoryMb(this.getAvailableMemoryMb() + memoryToAddMb);
  }
}
