<%@ include file="commons/header.jsp"%>
<%@ include file="commons/navigation.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="panel panel-primary">
                <div class="panel-heading" align="center">New Recipe</div>
                <div class="panel-body">
                    <form:form method="post" modelAttribute="recipe">
                        <!--<form:hidden path="id" />-->
                        <fieldset class="form-group">
                            <form:label path="recipeName">Recipe Name</form:label>
                            <form:input path="recipeName" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="recipeName" cssClass="text-warning" />
                        </fieldset>
                        <fieldset class="form-group">
                            <form:label path="vegIndicator">Veg Indicator</form:label>
                            <form:input path="vegIndicator" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="vegIndicator" cssClass="text-warning" />
                        </fieldset>
                        <fieldset class="form-group">
                            <form:label path="personIndicator">No. of Person can eat</form:label>
                            <form:input path="personIndicator" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="personIndicator" cssClass="text-warning" />
                        </fieldset>
                        <fieldset class="form-group">
                            <form:label path="ingredients">Ingredients</form:label>
                            <form:input path="ingredients" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="ingredients" cssClass="text-warning" />
                        </fieldset>
                        <fieldset class="form-group">
                            <form:label path="cookingInstruction">Cooking Instructions</form:label>
                            <form:input path="cookingInstruction" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="cookingInstruction" cssClass="text-warning" />
                        </fieldset>
                       <!-- <fieldset class="form-group">
                            <form:label path="creationDate">Creation Date</form:label>
                            <form:input path="creationDate" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="creationDate" cssClass="text-warning" />
                        </fieldset>-->

                        <button type="submit" class="btn btn-success">Add</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="commons/footer.jsp"%>