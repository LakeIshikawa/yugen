package yugen;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector2;
import com.lksoft.sweat.stateful.Fsm;
import com.lksoft.sweat.stateless.AnimationDef;
import com.lksoft.sweat.stateless.CommandDef;

/**
 * Fighter base class
 */
public class Fighter extends Fsm<Fighter, State<Fighter>, FighterHit> {
    // Constants
    public enum FightPosition {
        STANDING("S"),
        CROUCHING("C"),
        AIR("A");

        public String idchar;
        FightPosition(String idchar){
            this.idchar = idchar;
        }
    }

    // Physics
    public enum Physics{
        NONE,
        GROUND,
        AIR
    }

    // Default states
    public State<Fighter> idle = FighterState.IDLE;
    public State<Fighter> walk = FighterState.WALK;
    public State<Fighter> turn = FighterState.TURN;
    public State<Fighter> crouchturn = FighterState.CROUCHTURN;
    public State<Fighter> jumpstart = FighterState.JUMPSTART;
    public State<Fighter> jumping = FighterState.JUMPING;
    public State<Fighter> landing = FighterState.LANDING;
    public State<Fighter> stand2crouch = FighterState.STAND2CROUCH;
    public State<Fighter> crouching = FighterState.CROUCHING;
    public State<Fighter> crouch2stand = FighterState.CROUCH2STAND;
    public State<Fighter> running = FighterState.RUNNING;
    public State<Fighter> run = FighterState.RUN;
    public State<Fighter> backhop = FighterState.BACKHOP;
    public State<Fighter> backhopland= FighterState.BACKHOPLAND;
    public State<Fighter> groundDamage = FighterState.GROUNDDAMAGE;
    public State<Fighter> airDamage = FighterState.AIRDAMAGE;
    public State<Fighter> standingAttack = FighterState.STANDINGATTACK;
    public State<Fighter> crouchingAttack = FighterState.CROUCHINGATTACK;

    // Default animations
    public AnimationDef aDraw;
    public AnimationDef aIntro;
    public AnimationDef aTimeover;
    public AnimationDef aWin;

    public AnimationDef aIdle;
    public AnimationDef aTurn;
    public AnimationDef aCrouch2stand;
    public AnimationDef aStand2crouch;
    public AnimationDef aCrouching;
    public AnimationDef aCrouchturn;
    public AnimationDef aWalkfwd;
    public AnimationDef aWalkbwd;
    public AnimationDef aBackhop;
    public AnimationDef aBackhopland;
    public AnimationDef aRun;
    public AnimationDef aRunning;

    public AnimationDef aJumping;
    public AnimationDef aJumpingBwd;
    public AnimationDef aJumpingFwd;
    public AnimationDef aJumpstart;
    public AnimationDef aLanding;

    public AnimationDef aAirFall;
    public AnimationDef aDamageAHM;
    public AnimationDef aDamageCHH;
    public AnimationDef aDamageCHL;
    public AnimationDef aDamageCHM;
    public AnimationDef aDamageSHH;
    public AnimationDef aDamageSHL;
    public AnimationDef aDamageSHM;
    public AnimationDef aDamageSLH;
    public AnimationDef aDamageSLL;
    public AnimationDef aDamageSLM;

    public AnimationDef aGuardendA;
    public AnimationDef aGuardendC;
    public AnimationDef aGuardendS;
    public AnimationDef aGuardhitA;
    public AnimationDef aGuardhitC;
    public AnimationDef aGuardingA;
    public AnimationDef aGuardingC;
    public AnimationDef aGuardingS;

    public AnimationDef aKickSH;
    public AnimationDef aKickSL;
    public AnimationDef aKickSM;
    public AnimationDef aKickCH;
    public AnimationDef aKickCL;
    public AnimationDef aKickCM;
    public AnimationDef aKickAH;
    public AnimationDef aKickAL;
    public AnimationDef aKickAM;
    public AnimationDef aKickcloseSH;
    public AnimationDef aKickcloseSM;
    public AnimationDef aKickcloseSL;
    public AnimationDef aKickcloseCH;
    public AnimationDef aKickcloseCM;
    public AnimationDef aKickcloseCL;
    public AnimationDef aKickfwdSH;
    public AnimationDef aKickfwdSL;
    public AnimationDef aKickfwdSM;
    public AnimationDef aKickfwdCH;
    public AnimationDef aKickfwdCL;
    public AnimationDef aKickfwdCM;
    public AnimationDef aKickfwdAH;
    public AnimationDef aKickfwdAL;
    public AnimationDef aKickfwdAM;

    public AnimationDef aPunchSH;
    public AnimationDef aPunchSL;
    public AnimationDef aPunchSM;
    public AnimationDef aPunchCH;
    public AnimationDef aPunchCL;
    public AnimationDef aPunchCM;
    public AnimationDef aPunchAH;
    public AnimationDef aPunchAL;
    public AnimationDef aPunchAM;
    public AnimationDef aPunchcloseSH;
    public AnimationDef aPunchcloseSM;
    public AnimationDef aPunchcloseSL;
    public AnimationDef aPunchcloseCH;
    public AnimationDef aPunchcloseCM;
    public AnimationDef aPunchcloseCL;
    public AnimationDef aPunchfwdSH;
    public AnimationDef aPunchfwdSL;
    public AnimationDef aPunchfwdSM;
    public AnimationDef aPunchfwdCH;
    public AnimationDef aPunchfwdCL;
    public AnimationDef aPunchfwdCM;
    public AnimationDef aPunchfwdAH;
    public AnimationDef aPunchfwdAL;
    public AnimationDef aPunchfwdAM;



    // Default hits
    public FighterHit punchSLhit = new FighterHit();
    public FighterHit punchSMhit = new FighterHit();
    public FighterHit punchSHhit = new FighterHit();
    public FighterHit punchcloseSLhit = new FighterHit();
    public FighterHit punchcloseSMhit = new FighterHit();
    public FighterHit punchcloseSHhit = new FighterHit();
    public FighterHit punchfwdSLhit = new FighterHit();
    public FighterHit punchfwdSMhit = new FighterHit();
    public FighterHit punchfwdSHhit = new FighterHit();
    public FighterHit punchCLhit = new FighterHit();
    public FighterHit punchCMhit = new FighterHit();
    public FighterHit punchCHhit = new FighterHit();
    public FighterHit punchcloseCLhit = new FighterHit();
    public FighterHit punchcloseCMhit = new FighterHit();
    public FighterHit punchcloseCHhit = new FighterHit();
    public FighterHit punchfwdCLhit = new FighterHit();
    public FighterHit punchfwdCMhit = new FighterHit();
    public FighterHit punchfwdCHhit = new FighterHit();
    public FighterHit punchALhit = new FighterHit();
    public FighterHit punchAMhit = new FighterHit();
    public FighterHit punchAHhit = new FighterHit();
    public FighterHit punchfwdALhit = new FighterHit();
    public FighterHit punchfwdAMhit = new FighterHit();
    public FighterHit punchfwdAHhit = new FighterHit();
    public FighterHit kickSLhit = new FighterHit();
    public FighterHit kickSMhit = new FighterHit();
    public FighterHit kickSHhit = new FighterHit();
    public FighterHit kickcloseSLhit = new FighterHit();
    public FighterHit kickcloseSMhit = new FighterHit();
    public FighterHit kickcloseSHhit = new FighterHit();
    public FighterHit kickfwdSLhit = new FighterHit();
    public FighterHit kickfwdSMhit = new FighterHit();
    public FighterHit kickfwdSHhit = new FighterHit();
    public FighterHit kickCLhit = new FighterHit();
    public FighterHit kickCMhit = new FighterHit();
    public FighterHit kickCHhit = new FighterHit();
    public FighterHit kickcloseCLhit = new FighterHit();
    public FighterHit kickcloseCMhit = new FighterHit();
    public FighterHit kickcloseCHhit = new FighterHit();
    public FighterHit kickfwdCLhit = new FighterHit();
    public FighterHit kickfwdCMhit = new FighterHit();
    public FighterHit kickfwdCHhit = new FighterHit();
    public FighterHit kickALhit = new FighterHit();
    public FighterHit kickAMhit = new FighterHit();
    public FighterHit kickAHhit = new FighterHit();
    public FighterHit kickfwdALhit = new FighterHit();
    public FighterHit kickfwdAMhit = new FighterHit();
    public FighterHit kickfwdAHhit = new FighterHit();

    // Basilar commands
    public CommandDef runCmd = CommandDef.parse("{10} < ~F, !F >");
    public CommandDef backhopCmd = CommandDef.parse("{10} < ~B, !B >");

    // Default parameters
    public float speed_walk_fwd = 6.0f;
    public float speed_walk_bwd = -4.0f;
    public float speed_air_fwd = 3.0f;
    public float speed_air_bwd = -2.0f;
    public float speed_jump_up = 15.0f;
    public float speed_run_fwd = 9.0f;
    public int time_run_fwd = 12;
    public Vector2 speed_backhop = new Vector2(-9.0f, 5.0f);
    public int time_backhop = 9;
    public float air_gravity_y = -0.6f;

    // FightPosition
    public FightPosition fightPosition;
    public Physics physics;

    // References
    public Fsm opponent;
    public Stage stage;

    // Temp
    public int slidetime;

    // Initialization
    public Fighter() {
        setActive(false);

        // -- Default hit values
        // Punches
        punchSMhit.damageAnimHeight = FighterHit.DamageAnimHeight.HIGH;
    }

    @Override
    public State<Fighter> getInitialState(){
        return idle;
    }

    @Override
    public void statelessUpdate(){
        // Cache the animations if needed
        if( aIdle == null ){
            cacheAnimations();
        }

        boolean standing = fightPosition == FightPosition.STANDING;
        boolean crouching = fightPosition == FightPosition.CROUCHING;

        // Physics
        switch (physics){
            case GROUND:
                break;

            case AIR:
                vel.y = vel.y + air_gravity_y;
                if(pos.y < 0.0) {
                    vel.y = 0;
                    pos.y = 0;
                }
                break;
        }

        // Special and normal commands
        if( isCtrl() ){
            // Run
            if( standing && matchCommand(runCmd) ){
                if( aRunning != null ){
                    setCtrl(false);
                    changeState(running);
                    return;
                }
                if( aRun != null ){
                    setCtrl(false);
                    changeState(run);
                    return;
                }
            }

            // Backhop
            if( standing && aBackhop != null && matchCommand(backhopCmd) ){
                setCtrl(false);
                changeState(backhop);
                return;
            }

            // Normal attacks
            if( standing && aPunchSL != null && keyPress("B1") ){
                setCtrl(false);
                changeState(standingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aPunchcloseSL != null ){
                    // Close
                    setAnimation(aPunchcloseSL);
                    setAttackHit(punchcloseSLhit);
                } else if( keyHold("F") && aPunchfwdSL != null ){
                    // Fwd
                    setAnimation(aPunchfwdSL);
                    setAttackHit(punchfwdSLhit);
                } else {
                    // Normal
                    setAnimation(aPunchSL);
                    setAttackHit(punchSLhit);
                }
                return;
            }
            if( standing && aPunchSM != null && keyPress("B2") ){
                setCtrl(false);
                changeState(standingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aPunchcloseSM != null ){
                    // Close
                    setAnimation(aPunchcloseSM);
                    setAttackHit(punchcloseSMhit);
                } else if( keyHold("F") && aPunchfwdSM != null ){
                    // Fwd
                    setAnimation(aPunchfwdSM);
                    setAttackHit(punchfwdSMhit);
                } else {
                    // Normal
                    setAnimation(aPunchSM);
                    setAttackHit(punchSMhit);
                }
                return;
            }
            if( standing && aPunchSH != null && keyPress("B3") ){
                setCtrl(false);
                changeState(standingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aPunchcloseSH != null ){
                    // Close
                    setAnimation(aPunchcloseSH);
                    setAttackHit(punchcloseSHhit);
                } else if( keyHold("F") && aPunchfwdSH != null ){
                    // Fwd
                    setAnimation(aPunchfwdSH);
                    setAttackHit(punchfwdSHhit);
                } else {
                    // Normal
                    setAnimation(aPunchSH);
                    setAttackHit(punchSHhit);
                }
                return;
            }

            // Kicks
            if( standing && aKickSL != null && keyPress("B4") ){
                setCtrl(false);
                changeState(standingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aKickcloseSL != null ){
                    // Close
                    setAnimation(aKickcloseSL);
                    setAttackHit(kickcloseSLhit);
                } else if( keyHold("F") && aKickfwdSL != null ){
                    // Fwd
                    setAnimation(aKickfwdSL);
                    setAttackHit(kickfwdSLhit);
                } else {
                    // Normal
                    setAnimation(aKickSL);
                    setAttackHit(kickSLhit);
                }
                return;
            }
            if( standing && aKickSM != null && keyPress("B5") ){
                setCtrl(false);
                changeState(standingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aKickcloseSM != null ){
                    // Close
                    setAnimation(aKickcloseSM);
                    setAttackHit(kickcloseSMhit);
                } else if( keyHold("F") && aKickfwdSM != null ){
                    // Fwd
                    setAnimation(aKickfwdSM);
                    setAttackHit(kickfwdSMhit);
                } else {
                    // Normal
                    setAnimation(aKickSM);
                    setAttackHit(kickSMhit);
                }
                return;
            }
            if( standing && aKickSH != null && keyPress("B6") ){
                setCtrl(false);
                changeState(standingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aKickcloseSH != null ){
                    // Close
                    setAnimation(aKickcloseSH);
                    setAttackHit(kickcloseSHhit);
                } else if( keyHold("F") && aKickfwdSH != null ){
                    // Fwd
                    setAnimation(aKickfwdSH);
                    setAttackHit(kickfwdSHhit);
                } else {
                    // Normal
                    setAnimation(aKickSH);
                    setAttackHit(kickSHhit);
                }
                return;
            }
            
            // Crouching
            if( crouching && aPunchCL != null && keyPress("B1") ){
                setCtrl(false);
                changeState(crouchingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aPunchcloseCL != null ){
                    // Close
                    setAnimation(aPunchcloseCL);
                    setAttackHit(punchcloseCLhit);
                } else if( keyHold("F") && aPunchfwdCL != null ){
                    // Fwd
                    setAnimation(aPunchfwdCL);
                    setAttackHit(punchfwdCLhit);
                } else {
                    // Normal
                    setAnimation(aPunchCL);
                    setAttackHit(punchCLhit);
                }
                return;
            }
            if( crouching && aPunchCM != null && keyPress("B2") ){
                setCtrl(false);
                changeState(crouchingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aPunchcloseCM != null ){
                    // Close
                    setAnimation(aPunchcloseCM);
                    setAttackHit(punchcloseCMhit);
                } else if( keyHold("F") && aPunchfwdCM != null ){
                    // Fwd
                    setAnimation(aPunchfwdCM);
                    setAttackHit(punchfwdCMhit);
                } else {
                    // Normal
                    setAnimation(aPunchCM);
                    setAttackHit(punchCMhit);
                }
                return;
            }
            if( crouching && aPunchCH != null && keyPress("B3") ){
                setCtrl(false);
                changeState(crouchingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aPunchcloseCH != null ){
                    // Close
                    setAnimation(aPunchcloseCH);
                    setAttackHit(punchcloseCHhit);
                } else if( keyHold("F") && aPunchfwdCH != null ){
                    // Fwd
                    setAnimation(aPunchfwdCH);
                    setAttackHit(punchfwdCHhit);
                } else {
                    // Normal
                    setAnimation(aPunchCH);
                    setAttackHit(punchCHhit);
                }
                return;
            }

            // Kicks
            if( crouching && aKickCL != null && keyPress("B4") ){
                setCtrl(false);
                changeState(crouchingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aKickcloseCL != null ){
                    // Close
                    setAnimation(aKickcloseCL);
                    setAttackHit(kickcloseCLhit);
                } else if( keyHold("F") && aKickfwdCL != null ){
                    // Fwd
                    setAnimation(aKickfwdCL);
                    setAttackHit(kickfwdCLhit);
                } else {
                    // Normal
                    setAnimation(aKickCL);
                    setAttackHit(kickCLhit);
                }
                return;
            }
            if( crouching && aKickCM != null && keyPress("B5") ){
                setCtrl(false);
                changeState(crouchingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aKickcloseCM != null ){
                    // Close
                    setAnimation(aKickcloseCM);
                    setAttackHit(kickcloseCMhit);
                } else if( keyHold("F") && aKickfwdCM != null ){
                    // Fwd
                    setAnimation(aKickfwdCM);
                    setAttackHit(kickfwdCMhit);
                } else {
                    // Normal
                    setAnimation(aKickCM);
                    setAttackHit(kickCMhit);
                }
                return;
            }
            if( crouching && aKickCH != null && keyPress("B6") ){
                setCtrl(false);
                changeState(crouchingAttack);

                if( Math.abs(pos.x - opponent.pos.x) < frameHeight(aIdle) && aKickcloseCH != null ){
                    // Close
                    setAnimation(aKickcloseCH);
                    setAttackHit(kickcloseCHhit);
                } else if( keyHold("F") && aKickfwdCH != null ){
                    // Fwd
                    setAnimation(aKickfwdCH);
                    setAttackHit(kickfwdCHhit);
                } else {
                    // Normal
                    setAnimation(aKickCH);
                    setAttackHit(kickCHhit);
                }
                return;
            }
        }

        // Hit check
        if( isHit() ){
            // Block
            boolean standBlock = keyHold("B") && standing && getHit().guardflags.contains("S");
            boolean crouchBlock = keyHold("B") && crouching && getHit().guardflags.contains("C");
            if( standBlock ){
                // TODO Stand block
            }
            else if( crouchBlock ){
                // TODO Crouch block
            }
            else {
                // -- DAMAGE
                // Pause players
                pause(getHit().pausetime);
                opponent.pause(getHit().pausetime);

                // Play sparkle
                AnimationDef sparkle = opponent.getAnimation(getHit().sparkleAnimation);
                if( sparkle != null ){
                    Fsm sparkleFsm = loadFSM("yugen/Fx.java", "sparkle");
                    opponent.getAnimationPack().use();
                    sparkleFsm.setAnimationPack(opponent.getAnimationPack());
                    sparkleFsm.setAnimation(sparkle);
                    sparkleFsm.pos.set(
                            opponent.pos.x + getHit().sparkleX * (opponent.flip?-1:1),
                            opponent.pos.y + getHit().sparkleY);
                    sparkleFsm.setLayer(6);
                }

                // Air damage
                if( fightPosition == FightPosition.AIR ){
                    setAnimation(aAirFall);
                    vel.x = getHit().air_velocity * (flip ? -1 : 1);
                    vel.y = 0;
                    clearHit();
                    changeState(airDamage);
                    return;
                }

                // Falls
                if( getHit().fall ){
                    setAnimation(aAirFall);
                    vel.x = getHit().air_velocity * (flip ? -1 : 1);
                    vel.y = 15f;
                    clearHit();
                    changeState(airDamage);
                    return;
                }

                // Ground damage
                vel.x = getHit().ground_velocity * (flip ? -1 : 1);
                slidetime = getHit().ground_slidetime;

                String animName = "damage" + fightPosition.idchar + getHit().damageAnimHeight.idchar + getHit().damageAnimType.idchar;
                setAnimation(animName);
                clearHit();
                changeState(groundDamage);
                return;
            }
        }

        // Stage borders
        float leftBorder = 30 + stage.getCameraPos().x - stage.scene.camera_width/2;
        float rightBorder = stage.getCameraPos().x + stage.scene.camera_width/2 - 30;
        if( (pos.x == leftBorder && vel.x < 0) || (pos.x < leftBorder) ){
            vel.x = 0;
            pos.x = leftBorder;
        }
        if( (pos.x == rightBorder && vel.x > 0) || (pos.x > rightBorder) ){
            vel.x = 0;
            pos.x = rightBorder;
        }
    }

    // Cache all the animations
    private void cacheAnimations() {
        aDraw = getAnimation("draw");
        aIntro = getAnimation("intro");
        aTimeover = getAnimation("timeover");
        aWin = getAnimation("win");

        aIdle = getAnimation("idle");
        aTurn = getAnimation("turn");
        aCrouch2stand = getAnimation("crouch2stand");
        aStand2crouch = getAnimation("stand2crouch");
        aCrouching = getAnimation("crouching");
        aCrouchturn = getAnimation("crouchturn");
        aWalkfwd = getAnimation("walkfwd");
        aWalkbwd = getAnimation("walkbwd");
        aBackhop = getAnimation("backhop");
        aBackhopland = getAnimation("backhopland");
        aRun = getAnimation("run");
        aRunning = getAnimation("running");

        aJumping = getAnimation("jumping");
        aJumpingBwd = getAnimation("jumpingBwd");
        aJumpingFwd = getAnimation("jumpingFwd");
        aJumpstart = getAnimation("jumpstart");
        aLanding = getAnimation("landing");

        aAirFall = getAnimation("airFall");
        aDamageAHM = getAnimation("damageAHM");
        aDamageCHH = getAnimation("damageCHH");
        aDamageCHL = getAnimation("damageCHL");
        aDamageCHM = getAnimation("damageCHM");
        aDamageSHH = getAnimation("damageSHH");
        aDamageSHL = getAnimation("damageSHL");
        aDamageSHM = getAnimation("damageSHM");
        aDamageSLH = getAnimation("damageSLH");
        aDamageSLL = getAnimation("damageSLL");
        aDamageSLM = getAnimation("damageSLM");

        aGuardendA = getAnimation("guardendA");
        aGuardendC = getAnimation("guardendC");
        aGuardendS = getAnimation("guardendS");
        aGuardhitA = getAnimation("guardhitA");
        aGuardhitC = getAnimation("guardhitC");
        aGuardingA = getAnimation("guardingA");
        aGuardingC = getAnimation("guardingC");
        aGuardingS = getAnimation("guardingS");

        aKickSH = getAnimation("kickSH");
        aKickSL = getAnimation("kickSL");
        aKickSM = getAnimation("kickSM");
        aKickCH = getAnimation("kickCH");
        aKickCL = getAnimation("kickCL");
        aKickCM = getAnimation("kickCM");
        aKickAH = getAnimation("kickAH");
        aKickAL = getAnimation("kickAL");
        aKickAM = getAnimation("kickAM");
        aKickcloseSH = getAnimation("kickcloseSH");
        aKickcloseSM = getAnimation("kickcloseSM");
        aKickcloseSL = getAnimation("kickcloseSL");
        aKickcloseCH = getAnimation("kickcloseCH");
        aKickcloseCM = getAnimation("kickcloseCM");
        aKickcloseCL = getAnimation("kickcloseCL");
        aKickfwdSH = getAnimation("kickfwdSH");
        aKickfwdSL = getAnimation("kickfwdSL");
        aKickfwdSM = getAnimation("kickfwdSM");
        aKickfwdCH = getAnimation("kickfwdCH");
        aKickfwdCL = getAnimation("kickfwdCL");
        aKickfwdCM = getAnimation("kickfwdCM");
        aKickfwdAH = getAnimation("kickfwdAH");
        aKickfwdAL = getAnimation("kickfwdAL");
        aKickfwdAM = getAnimation("kickfwdAM");

        aPunchSH = getAnimation("punchSH");
        aPunchSL = getAnimation("punchSL");
        aPunchSM = getAnimation("punchSM");
        aPunchCH = getAnimation("punchCH");
        aPunchCL = getAnimation("punchCL");
        aPunchCM = getAnimation("punchCM");
        aPunchAH = getAnimation("punchAH");
        aPunchAL = getAnimation("punchAL");
        aPunchAM = getAnimation("punchAM");
        aPunchcloseSH = getAnimation("punchcloseSH");
        aPunchcloseSM = getAnimation("punchcloseSM");
        aPunchcloseSL = getAnimation("punchcloseSL");
        aPunchcloseCH = getAnimation("punchcloseCH");
        aPunchcloseCM = getAnimation("punchcloseCM");
        aPunchcloseCL = getAnimation("punchcloseCL");
        aPunchfwdSH = getAnimation("punchfwdSH");
        aPunchfwdSL = getAnimation("punchfwdSL");
        aPunchfwdSM = getAnimation("punchfwdSM");
        aPunchfwdCH = getAnimation("punchfwdCH");
        aPunchfwdCL = getAnimation("punchfwdCL");
        aPunchfwdCM = getAnimation("punchfwdCM");
        aPunchfwdAH = getAnimation("punchfwdAH");
        aPunchfwdAL = getAnimation("punchfwdAL");
        aPunchfwdAM = getAnimation("punchfwdAM");
    }
}

/**
 * Fighter state machine
 */
enum FighterState implements State<Fighter> {
    IDLE() {
        public void enter(Fighter f){
            // Save stage
            f.stage = (Stage) f.getFSM("stage");
            // Save opponent
            if( f.opponent == null ) {
                f.opponent = (f == f.getFSM("p1")) ? f.getFSM("p2") : f.getFSM("p1");
            }

            f.setAnimation("idle");
            f.fightPosition = Fighter.FightPosition.STANDING;
            f.physics = Fighter.Physics.GROUND;
            f.setCtrl(true);
            f.setLayer(5);
            f.vel.set(0,0);
            f.pos.y = 0;
        }

        public void update(Fighter f) {
            // Turn
            if( !f.facing(f.opponent) ){
                f.changeState(f.turn);
                return;
            }

            // Walk
            if( f.keyHold("F") ^ f.keyHold("B") ){
                f.changeState(f.walk);
                return;
            }

            // Jump
            if( f.keyHold("U") ){
                f.changeState(f.jumpstart);
                return;
            }

            // Crouch
            if( f.keyHold("D") ){
                f.changeState(f.stand2crouch);
                return;
            }
        }
    },

    WALK {
        public void enter(Fighter f) {
            f.physics = Fighter.Physics.NONE;
        }

        public void update(Fighter f) {
            // Fwd
            if( f.keyHold("F") && !f.keyHold("B") ){
                f.vel.x = f.speed_walk_fwd * (f.flip ? -1 : 1);
                f.setAnimation(f.aWalkfwd);
            }

            // Bwd
            if( f.keyHold("B") && !f.keyHold("F") ){
                f.vel.x = f.speed_walk_bwd * (f.flip ? -1 : 1);
                f.setAnimation(f.aWalkbwd);
            }

            // Turn
            if( !f.facing(f.opponent) ){
                f.changeState(f.turn);
                return;
            }

            // Stop walking
            if( !(f.keyHold("F") ^ f.keyHold("B")) ){
                f.changeState(f.idle);
            }

            // Jump
            if( f.keyHold("U") ){
                f.changeState(f.jumpstart);
                return;
            }

            // Crouch
            if( f.keyHold("D") && f.aCrouching != null ){
                f.changeState(f.stand2crouch);
                return;
            }
        }
    },

    TURN {
        public void enter(Fighter f){
            f.vel.set(0,0);
            f.setAnimation(f.aTurn);
            f.flip = !f.flip;
        }

        public void update(Fighter f) {
            if( f.aTurn == null || f.getAnimCycles() == 1 ){
                f.changeState(f.idle);
                return;
            }
        }
    },

    CROUCHTURN {
        public void enter(Fighter f){
            f.vel.set(0,0);
            f.setAnimation(f.aCrouchturn);
            f.flip = !f.flip;
        }

        public void update(Fighter f) {
            if( f.aCrouchturn == null || f.getAnimCycles() == 1 ){
                f.changeState(f.crouching);
                return;
            }
        }
    },

    JUMPSTART {
        public void enter(Fighter f){
            f.setAnimation(f.aJumpstart);
        }

        public void update(Fighter f) {
            // Jump up
            if( f.aJumpstart == null || f.getAnimCycles() == 1 ){
                // Set proper animation
                if( f.keyHold("F") && f.aJumpingFwd != null ) f.setAnimation(f.aJumpingFwd);
                else if ( f.keyHold("B") && f.aJumpingBwd != null ) f.setAnimation(f.aJumpingBwd);
                else f.setAnimation(f.aJumping);

                // Jump
                f.vel.y = f.speed_jump_up;
                f.changeState(f.jumping);
                return;
            }
        }
    },

    JUMPING {
        public void enter(Fighter f){
            f.fightPosition = Fighter.FightPosition.AIR;
            f.physics = Fighter.Physics.AIR;
        }
        public void update(Fighter f) {
            // Controls
            if( f.keyHold("F") ){
                f.vel.x  = f.speed_air_fwd * (f.flip ? -1 : 1);
            }
            if( f.keyHold("B") ){
                f.vel.x  = f.speed_air_bwd * (f.flip ? -1 : 1);
            }

            // Landing
            if( f.pos.y < 0 ){
                f.changeState(f.landing);
                return;
            }
        }
    },

    LANDING {
        public void enter(Fighter f){
            f.setAnimation(f.aLanding);
            f.vel.set(0,0);
        }

        public void update(Fighter f) {
            if( f.aLanding == null || f.getAnimCycles() == 1 ){
                f.changeState(f.idle);
            }
        }
    },

    STAND2CROUCH {
        public void enter(Fighter f){
            f.fightPosition = Fighter.FightPosition.CROUCHING;
            f.setAnimation(f.aStand2crouch);
            f.vel.set(0,0);
        }
        public void update(Fighter f) {
            if( f.aStand2crouch == null || f.getAnimCycles() == 1 ){
                f.changeState(f.crouching);
                return;
            }
        }
    },

    CROUCHING {
        public void enter(Fighter f){
            f.setCtrl(true);
            f.setAnimation(f.aCrouching);
        }

        public void update(Fighter f) {
            // Stop crouching
            if( !f.keyHold("D") ){
                f.changeState(f.crouch2stand);
                return;
            }

            // Turn
            if( !f.facing(f.opponent) ){
                f.changeState(f.crouchturn);
                return;
            }
        }
    },

    CROUCH2STAND {
        public void enter(Fighter f){
            f.setAnimation(f.aCrouch2stand);
        }

        public void update(Fighter f) {
            if( f.aCrouch2stand == null || f.getAnimCycles() == 1 ){
                f.changeState(f.idle);
                return;
            }
        }
    },

    RUNNING {
        public void enter(Fighter f){
            f.setAnimation(f.aRunning);
            f.vel.x = f.speed_run_fwd * (f.flip?-1:1);
        }

        public void update(Fighter f) {
            if( !f.keyHold("F") ){
                f.changeState(f.idle);
                return;
            }
        }
    },

    RUN {
        public void enter(Fighter f){
            f.setAnimation(f.aRun);
            f.vel.x = f.speed_run_fwd * (f.flip?-1:1);
        }

        public void update(Fighter f) {
            if( f.getStatetime() >= f.time_run_fwd ){
                f.vel.set(0,0);
                f.changeState(f.idle);
                return;
            }
        }
    },

    BACKHOP {
        public void enter(Fighter f){
            f.setAnimation(f.aBackhop);
            f.vel.x = f.speed_backhop.x * (f.flip?-1:1);
            f.vel.y = f.speed_backhop.y;
            f.physics = Fighter.Physics.AIR;
        }

        public void update(Fighter f) {
            // If there is a backhopland, just look at y value
            if( f.aBackhopland != null ){
                if( f.pos.y < 0 ){
                    f.vel.set(0,0);
                    f.changeState(f.backhopland);
                    return;
                }
            } else {
                if( f.getStatetime() >= f.time_backhop ){
                    f.changeState(f.idle);
                    return;
                }
            }
        }
    },

    BACKHOPLAND {
        public void enter(Fighter f){
            f.setAnimation(f.aBackhopland);
        }

        public void update(Fighter f) {
            if( f.getAnimCycles() == 1 ){
                f.changeState(f.idle);
                return;
            }
        }
    },

    GROUNDDAMAGE {
        public void enter(Fighter f){
            f.physics = Fighter.Physics.NONE;
        }
        public void update(Fighter f) {
            if( f.getStatetime() == f.slidetime ){
                f.changeState(f.idle);
            }
        }
    },

    AIRDAMAGE {
        public void enter(Fighter f){
            f.fightPosition = Fighter.FightPosition.AIR;
            f.physics = Fighter.Physics.AIR;
        }
        public void update(Fighter f) {
            if( f.pos.y < 0 ){
                f.changeState(f.landing);
                return;
            }
        }
    },

    STANDINGATTACK {
        public void enter(Fighter f){
            f.setLayer(6);
            f.vel.x = 0;
            f.setCtrl(false);
        }

        public void update(Fighter f) {
            if( f.getAnimCycles() == 1 ){
                f.changeState(f.idle);
                return;
            }
        }
    },

    CROUCHINGATTACK {
        public void enter(Fighter f){
            f.setLayer(6);
            f.vel.x = 0;
            f.setCtrl(false);
        }

        public void update(Fighter f) {
            if( f.getAnimCycles() == 1 ){
                f.changeState(f.crouching);
                return;
            }
        }
    },
    ;

    public void enter(Fighter entity){}
    public void update(Fighter entity) {}
    public void exit(Fighter entity){}
    public boolean onMessage(Fighter entity, Telegram telegram) {return false;}
}
