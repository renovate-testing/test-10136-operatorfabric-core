import {ThirdActionState} from "@ofStates/third-action.state";
import {createSelector} from "@ngrx/store";
import {AppState} from "@ofStore/index";

const selectThirdActions = (state:AppState) => state.thirdAction;

export const selectThirdActionList = createSelector(
    selectThirdActions,
    (state:ThirdActionState) => state.thirdActions
);