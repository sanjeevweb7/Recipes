<%@ include file="commons/header.jsp"%>
<%@ include file="commons/navigation.jsp"%>

<div class="container">
    <div align="center">
        <a type="button" class="btn btn-primary btn-md" href="/addRecipe">Add New Recipe</a>
    </div>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading" align="center">
            <h3>Today's Available Recipe</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Recipe Name</th>
                    <th>Veg Indicator</th>
                    <th>No. of Person can eat</th>
                    <th>Ingredients</th>
                    <th>Cooking Instruction</th>
                    <th>Creation Date</th>
                    <th>Updated By</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="recipe" items="${recipesList}">
                    <tr>
                        <td>${recipe.recipeName}</td>
                        <td>${recipe.vegIndicator}</td>
                        <td>${recipe.personIndicator}</td>
                        <td>${recipe.ingredients}</td>
                        <td>${recipe.cookingInstruction}</td>
                        <td>${recipe.creationDate}</td>
                        <td>${recipe.updatedBy}</td>
                        <td><a href="/updateRecipe?id=${recipe.id}">Edit</a></td>
                        <td><a href="/deleteRecipe?id=${recipe.id}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
<%@ include file="commons/footer.jsp"%>