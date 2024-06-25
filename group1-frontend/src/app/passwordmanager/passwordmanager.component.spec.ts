import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordmanagerComponent } from './passwordmanager.component';

describe('PasswordmanagerComponent', () => {
  let component: PasswordmanagerComponent;
  let fixture: ComponentFixture<PasswordmanagerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PasswordmanagerComponent]
    });
    fixture = TestBed.createComponent(PasswordmanagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
