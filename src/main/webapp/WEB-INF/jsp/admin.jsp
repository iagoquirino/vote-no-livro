<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="votenolivro"%>
<votenolivro:layout>
			<section>
						<c:url var="add" value="/admin/add" />
						<form method="post" action="${add}"  class="form-vertical">
	      					<div class="input-prepend">
				                <input type="text" placeholder="Nome Livro" name="nome">
			                </div>
		                <input type="submit" value="Add" class="btn btn-inverse"/>
		            </form>
			</section>
</votenolivro:layout>