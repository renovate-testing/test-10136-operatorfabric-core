import {ThirdActionHolderState, ThirdActionState} from "@ofStates/third-action.state";
import {createFeatureSelector, createSelector} from "@ngrx/store";
import {AppState} from "@ofStore/index";
import {Action as ThirdAction} from "@ofModel/thirds.model";

export const getThirdActionSelector =createFeatureSelector<AppState,ThirdActionState>('thirdActions');

export const getThirdActionHolderSelector = createFeatureSelector<AppState,ThirdActionHolderState>('thirdActionHolders');

export const selectThirdAction = createSelector(
    getThirdActionHolderSelector,
    getThirdActionSelector,
    (thirdActionHolderState,thirdActionState)=>{
        const id = thirdActionHolderState.selectedThirdActionHolderId;
        const holders = thirdActionHolderState.entities;
        const currentThirdActionHolder = holders[id];
        const allThirdActions=thirdActionState.entities;
        return currentThirdActionHolder.actionIds.map(tAId=>allThirdActions[tAId]);
    }
);
