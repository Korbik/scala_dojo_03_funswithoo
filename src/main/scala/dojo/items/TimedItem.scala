package dojo.items

trait TimedItem {
  private[TimedItem] var startTime: Int = Int.MaxValue

  def ready(now: Int): Boolean = (now - (startTime + delay)) >= 0

  def act(now: Int): Option[Any] = if (ready(now)) { Some(action) } else { None }

  def startClock(now: Int) {
    startTime = now
  }
  
  def delay: Int

  protected def action: Any

}
