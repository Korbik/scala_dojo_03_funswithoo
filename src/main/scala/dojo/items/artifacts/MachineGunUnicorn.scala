package dojo.items.artifacts
import dojo.items.TimedItem



class MachineGunUnicorn(id:Long) extends ArtifactItem(id) with TimedItem {
	def delay = 2
	def action = "Bam-Bam"
}
