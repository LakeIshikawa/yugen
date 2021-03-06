package yugen;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Rectangle;
import com.lksoft.sweat.stateful.Fsm;
import com.lksoft.sweat.stateless.SceneDef;

/**
 * Fighter base class
 */
public class Stage extends Fsm<Stage, StageState, Object> {
    int p1_start_x = -130;
    int p1_start_y = 0;
    int p2_start_x = 130;
    int p2_start_y = 0;

    int fighters_height = 200;

    // References
    Fsm p1, p2;
    SceneDef scene;

    // Fighters collision
    Rectangle p1Rect = new Rectangle();
    Rectangle p2Rect = new Rectangle();

    @Override
    public StageState getInitialState(){
        return StageState.INIT;
    }

    @Override
    public void statelessUpdate(){}
}

/**
 * Fighter state machine
 */
enum StageState implements State<Stage> {
    INIT() {
        public void enter(Stage stage){
            // Load scene
            StageResources stageResources = stage.getClass().getAnnotation(StageResources.class);
            stage.scene = stage.loadScene(stageResources.scn());

            // Set players
            Fsm p1 = stage.getFSM("p1");
            Fsm p2 = stage.getFSM("p2");
            stage.p1 = p1;
            stage.p2 = p2;

            // Set keys
            p1.setKeySettings(stage.getSettings().getP1Keys());
            p2.setKeySettings(stage.getSettings().getP2Keys());

            // Set initial positions
            p1.pos.set(stage.p1_start_x, stage.p1_start_y);
            p2.pos.set(stage.p2_start_x, stage.p2_start_y);

            // Set scale and facing direction
            p1.scale *= (float)stage.fighters_height / p1.frameHeight(p1.getAnimation("idle"));
            p2.scale *= (float)stage.fighters_height / p2.frameHeight(p2.getAnimation("idle"));
            p2.flip = true;

            // Add collisionts
            p1.addCollisionTarget(p2);
            p2.addCollisionTarget(p1);

            // Activate
            p1.setActive(true);
            p2.setActive(true);

            // Init camera
            stage.initCamera(stage.scene.camera_x, stage.scene.camera_y,
                    stage.scene.camera_width, stage.scene.camera_height);
        }

        public void update(Stage stage) {
            // Camera movement
            int minx = stage.scene.area_l + stage.scene.camera_width/2;
            int maxx = stage.scene.area_r - stage.scene.camera_width/2;
            int px = (int)(stage.p1.pos.x + stage.p2.pos.x)/2;
            px = Math.min(Math.max(px, minx), maxx);
            stage.setCamera(px, stage.scene.camera_y);

            // Fighters physical collision
            int collWidth = (int) (stage.fighters_height * 0.6f);
            Rectangle r1 = stage.p1Rect;
            Rectangle r2 = stage.p2Rect;
            r1.set(stage.p1.pos.x - collWidth/2, stage.p1.pos.y, collWidth/2, collWidth);
            r2.set(stage.p2.pos.x - collWidth/2, stage.p2.pos.y, collWidth/2, collWidth);

            if( stage.p1Rect.overlaps(stage.p2Rect) ){
                float overlapWidth = r1.x < r2.x ? r1.width - (r2.x - r1.x) : r2.width - (r1.x - r2.x);
                float step = 4 + overlapWidth/2;

                float f1Step;
                float f2Step;

                // Push back!
                if( r1.x < r2.x ){
                    f1Step = -step/2;
                    f2Step = step/2;
                } else {
                    f1Step = step/2;
                    f2Step = -step/2;
                }

                stage.p1.pos.x += f1Step;
                stage.p2.pos.x += f2Step;
            }
        }
    };

    public void update(Stage entity) {}
    public void enter(Stage entity){}
    public void exit(Stage entity){}
    public boolean onMessage(Stage entity, Telegram telegram) {return false;}
}
