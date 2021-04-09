public class Mac extends Computer {

  public Mac(int availableMemoryMb, int availableDiskSpaceMb) {
    super(availableMemoryMb, availableDiskSpaceMb);
  }

  @Override
  public String toString() {
    return super.toString() + " Operating System: Mac";
  }

  public void upgradeMemory(int memoryToAddMb) throws Exception {
    throw new Exception("Memory cannot be upgraded");
  }
}
