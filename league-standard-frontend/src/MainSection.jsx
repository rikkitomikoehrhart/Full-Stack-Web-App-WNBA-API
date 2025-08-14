import TeamsList from './components/Teams/TeamsList';

function MainSection() {
    
    
    return (
        <>
            <div className="container text-center">
                <div className="row">
                    <div className="col-4 d-one d-md-block">

                    </div>

                    <div className="mt-4 col-md-8 col-12">
                        <TeamsList />
                    </div>
                </div>
            </div>
        </>
    );
}

export default MainSection;