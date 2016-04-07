package org.researchstack.skin.task;

import android.content.Context;

import org.researchstack.backbone.result.TaskResult;
import org.researchstack.backbone.step.Step;
import org.researchstack.skin.TaskProvider;

/**
 * Created by bradleymcdermott on 10/16/15.
 */
public class SignInTask extends OnboardingTask
{
    private boolean hasPasscode;

    public static final int    MINIMUM_STEPS = 0;
    public static final String ID_EMAIL      = "ID_EMAIL";
    public static final String ID_PASSWORD   = "ID_PASSWORD";

    public SignInTask(Context context)
    {
        super(TaskProvider.TASK_ID_SIGN_IN);
    }

    @Override
    public Step getStepAfterStep(Step step, TaskResult result)
    {
        Step nextStep = null;

        if(step == null)
        {
            if(! hasPasscode)
            {
                nextStep = getPassCodeCreationStep();
            }
            else
            {
                nextStep = getSignInStep();
            }
        }
        else if(step.getIdentifier().equals(SignUpPassCodeCreationStepIdentifier))
        {
            nextStep = getSignInStep();
        }
        return nextStep;
    }

    @Override
    public Step getStepBeforeStep(Step step, TaskResult result)
    {
        // go back to onboarding
        return null;
    }

    @Override
    public TaskProgress getProgressOfCurrentStep(Step step, TaskResult result)
    {
        return new TaskProgress(0, MINIMUM_STEPS);
    }

    public void setHasPasscode(boolean hasPasscode)
    {
        this.hasPasscode = hasPasscode;
    }
}
