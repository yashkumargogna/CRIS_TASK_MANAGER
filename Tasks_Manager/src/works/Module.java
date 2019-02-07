package works;

import java.io.Serializable;

public class Module implements Serializable 
{
		String mod_id;
		String mod_name;
		String mod_desp;
		String proj_rel;
		public String getMod_id() {
			return mod_id;
		}
		public void setMod_id(String mod_id) {
			this.mod_id = mod_id;
		}
		public String getMod_name() {
			return mod_name;
		}
		public void setMod_name(String mod_name) {
			this.mod_name = mod_name;
		}
		public String getMod_desp() {
			return mod_desp;
		}
		public void setMod_desp(String mod_desp) {
			this.mod_desp = mod_desp;
		}
		public String getProj_rel() {
			return proj_rel;
		}
		public void setProj_rel(String proj_rel) {
			this.proj_rel = proj_rel;
		}
		
}
