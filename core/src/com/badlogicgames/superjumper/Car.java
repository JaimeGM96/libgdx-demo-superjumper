package com.badlogicgames.superjumper;

/**
 * Created by Jaime on 26/01/2018.
 */

public class Car extends DynamicGameObject {
    public static final float CAR_WIDTH = 1;
    public static final float CAR_HEIGHT = 2;
    public static final int CAR_TYPE_STATIC = 0;
    public static final int CAR_TYPE_MOVING = 1;
    public static final int CAR_STATE_NORMAL = 0;
    public static final int CAR_STATE_PULVERIZING = 1;
    public static final float CAR_PULVERIZE_TIME = 0.2f * 4;
    public static final float CAR_VELOCITY = 2;

    int type;
    int state;
    float stateTime;

    public Car (int type, float x, float y) {
        super(x, y, CAR_WIDTH, CAR_HEIGHT);
        this.type = type;
        this.state = CAR_STATE_NORMAL;
        this.stateTime = 0;
        if (type == CAR_TYPE_MOVING) {
            velocity.x = CAR_VELOCITY;
        }
    }

    public void update (float deltaTime) {
        if (type == CAR_TYPE_MOVING) {
            position.add(velocity.x * deltaTime, 0);
            bounds.x = position.x - CAR_WIDTH / 2;
            bounds.y = position.y - CAR_HEIGHT / 2;

            if (position.x < CAR_WIDTH / 2) {
                velocity.x = -velocity.x;
                position.x = CAR_HEIGHT / 2;
            }
            if (position.x > World.WORLD_WIDTH - CAR_WIDTH / 2) {
                velocity.x = -velocity.x;
                position.x = World.WORLD_WIDTH - CAR_HEIGHT / 2;
            }
        }

        stateTime += deltaTime;
    }

    public void pulverize () {
        state = CAR_STATE_PULVERIZING;
        stateTime = 0;
        velocity.x = 0;
    }
}
