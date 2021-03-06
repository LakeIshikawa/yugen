package yugen.screens;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.lksoft.sweat.stateful.Fsm;



/**
 * Stage select screen
 */
public class StageSelect extends Fsm<StageSelect, StageSelectState, Object> {
    public StageSelectState getInitialState(){
        return StageSelectState.INIT;
    }
    public void statelessUpdate() {}
}

/**
 * Stage select screen
 */
enum StageSelectState implements State<StageSelect>{
    INIT(){
        public void enter(StageSelect fsm){
            fsm.loadFSM("chars/ryu/Ryu.java", "p1");
            fsm.loadFSM("chars/ryu/Ryu.java", "p2");
            fsm.loadFSM("stages/lionking/Lionking.java", "stage");

            fsm.destroyFSM(fsm.getName());
        }

        public void update(StageSelect fsm) {}
        public void exit(StageSelect fsm){}
        public boolean onMessage(StageSelect entity, Telegram telegram) {return false;}
    }
}