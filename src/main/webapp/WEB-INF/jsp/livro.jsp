<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="votenolivro"%>
<votenolivro:layout>
			<section>
				<p>Clique em uma das opções para confirmar <strong>Seu Voto</strong>!</p>
				<div class="mockup-content">
					<p>Pea horseradish azuki bean lettuce avocado asparagus okra.</p>
						<c:url var="votar" value="/livro/votar" />
						<c:url var="computarvotos" value="/livro/computarvotos" />
						<form:form method="post" cssClass="ac-custom ac-radio ac-swirl" action="${votar}" commandName="livro" class="form-vertical">
							<c:forEach items="${livros}" var="livro" varStatus="status">
								<ul>
									<li>
										<label for="r${status.index}"><form:radiobutton id="r${status.index}" path="id" value="${livro.id}" /><font color="#ffffff">${livro.nome}</font></label>
									</li>
								</ul>
							</c:forEach>
							<input type="submit" class="btn btn-1 btn-1b" value="Votar" />
						</form:form>
						<div class="morph-button morph-button-modal morph-button-modal-2 morph-button-fixed active">
							
							<button type="button">Ver Ranking!</button>
							<div class="morph-content">
								<div>
									<div class="content-style-form content-style-form-1">
										<span class="icon icon-close">Close the dialog</span>									
										<h2>Seus Dados!</h2>
										<form:form method="post" action="${computarvotos}" commandName="pessoa" class="form-vertical">
											<p><label>Nome</label>
											<form:input path="nome" />
											<p><label>Email</label>
											<form:input path="email" />
											<p><input type="submit" type="submit" class="btn btn-2 btn-2a" value="Confirmar" /></p>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					<p>Kohlrabi radish okra azuki bean corn fava bean mustard tigernut juccama green bean celtuce collard greens avocado quandong.</p>
				</div><!-- /form-mockup -->
			</section>
</votenolivro:layout>