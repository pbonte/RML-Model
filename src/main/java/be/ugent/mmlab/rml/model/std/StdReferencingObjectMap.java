package be.ugent.mmlab.rml.model.std;

import be.ugent.mmlab.rml.model.JoinCondition;
import be.ugent.mmlab.rml.model.PredicateObjectMap;
import be.ugent.mmlab.rml.model.RDFTerm.ReferencingObjectMap;
import be.ugent.mmlab.rml.model.TriplesMap;
import java.util.HashSet;
import java.util.Set;

/**
 *************************************************************************
 *
 * RML - Model : Referencing Object Map Implementation
 *
 * A Referencing Object Map allows using the subjects
 * of another Triples Map as the object generated by 
 * a Predicate-Object Map.
 * 
 * @author mielvandersande, andimou
 * 
 ***************************************************************************
 */
public class StdReferencingObjectMap implements ReferencingObjectMap {

    private TriplesMap parentTriplesMap;
    private HashSet<JoinCondition> joinConditions;
    private PredicateObjectMap predicateObjectMap;
    protected TriplesMap ownTriplesMap;

    /**
     *
     * @param predicateObjectMap
     * @param parentTriplesMap
     * @param joinConditions
     */
    public StdReferencingObjectMap(PredicateObjectMap predicateObjectMap,
            TriplesMap parentTriplesMap, Set<JoinCondition> joinConditions) {
        setPredicateObjectMap(predicateObjectMap);
        setParentTriplesMap(parentTriplesMap);
        setJoinConditions(joinConditions);
        setOwnTriplesMap(parentTriplesMap);

    }
    
    @Override
    public void setJoinConditions(Set<JoinCondition> joinConditions) {
        this.joinConditions = new HashSet<JoinCondition>();
        this.joinConditions.addAll(joinConditions);
    }

    @Override
    public String getChildReference() {
        return predicateObjectMap.getOwnTriplesMap().getLogicalSource().getIterator();
    }

    @Override
    public Set<JoinCondition> getJoinConditions() {
        return joinConditions;
    }

    @Override
    public String getParentReference() {
        return parentTriplesMap.getLogicalSource().getIterator();
    }

    @Override
    public TriplesMap getOwnTriplesMap() {
        return ownTriplesMap;
    }

    @Override
    public TriplesMap getParentTriplesMap() {
        return parentTriplesMap;
    }

    @Override
    public PredicateObjectMap getPredicateObjectMap() {
        return predicateObjectMap;
    }

    @Override
    public void setPredicateObjectMap(PredicateObjectMap predicateObjectMap) {
        // Update predicateObjectMap if not contains this object map
        if (predicateObjectMap != null) {
            if (predicateObjectMap.getReferencingObjectMaps() == null) {
                predicateObjectMap
                        .setReferencingObjectMap(new HashSet<ReferencingObjectMap>());
            }
            predicateObjectMap.getReferencingObjectMaps().add(this);
        }
        this.predicateObjectMap = predicateObjectMap;
    }

    @Override
    public void setOwnTriplesMap(TriplesMap ownTriplesMap) {
        this.ownTriplesMap = ownTriplesMap;
    }

    @Override
    public void setParentTriplesMap(TriplesMap triplesMap) {
        this.parentTriplesMap = triplesMap;
    }
}
